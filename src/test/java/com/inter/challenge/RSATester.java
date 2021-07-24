package com.inter.challenge;

import com.inter.challenge.component.RSAComponent;
import com.inter.challenge.model.User;
import com.inter.challenge.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.*;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 20/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RSATester {

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp+q9bRGIP0h/71DOVJ2QirBbIfsCZH3OoqcNsQgV98dHrqRP3PIpbqUfDtNg10k9/G40xLldtky3xtFKIAtiSmwSzGPMZ6b9XW9ovvkPqsdTlT+nLIFAofcWZd0+PPf7ZtcvO0CWg1laOd4BDTU5+uDzeBi6QDPMBz/jR4ValPoTMm0L64UmXnvVoD8Rbht7uCLmQeywL5t5xdHb0ecHgm1orGxAEbImKTLpQv1MCtsWDv9HvZH0SplwhY/UphOYxVij+5zDCwUCUBEA+gb1N4G9sbhPHRi1yea9FjE1ibBjr4c54Q2U6NDrzxrlX7BpEHdV9r93zAXM8fED2xkSfwIDAQAB";
    private RSAComponent component = new RSAComponent();

    @Test
    @Order(1)
    public void writeKeysToFile() throws Exception {
        KeyPair pair = component.autoGenerateKeys();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        byte[] publicKeyByte = publicKey.getEncoded();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyByte);
        Base64.Encoder encoder = Base64.getEncoder();
        System.out.println(publicKeyString);
        System.out.println(encoder.encodeToString(privateKey.getEncoded()));

        component.prepareAndSaveKeys("test", publicKey, privateKey);

        Key publicFromFile = component.readFromFile("test-public.key",true);
        assertNotNull(publicFromFile);
        assertEquals(publicFromFile, publicKey);
    }

    @Test
    @Order(2)
    public void createFromString() throws Exception {
        PublicKey pulicKey = component.generateKeyFromString(PUBLIC_KEY);
        assertNotNull(pulicKey);
    }

    @Test
    @Order(3)
    public void saveEncryptedUserInfo() throws Exception {
        PublicKey pulicKey = component.generateKeyFromString(PUBLIC_KEY);

        User user = new User();
        user.setName("Regis");
        user.setEmail("regis@bancointer.com.br");

        String encryptedName = component.encrypt(user.getName(), pulicKey);
        String encryptedEmail = component.encrypt(user.getEmail(), pulicKey);

        assertNotEquals(user.getName(), encryptedName);
        assertNotEquals(user.getEmail(), encryptedEmail);
    }

}
