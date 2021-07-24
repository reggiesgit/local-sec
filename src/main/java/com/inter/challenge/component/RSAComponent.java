package com.inter.challenge.component;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 20/07/2021
 */

@Component
public class RSAComponent {

    public String encrypt(String text, String stringKey) throws Exception {
        PublicKey key = generateKeyFromString(stringKey);
        return encrypt(text, key);
    }

    public static String encrypt(String text, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        String result = Base64.getEncoder().encodeToString(encrypted);
        return result;
    }

    public KeyPair autoGenerateKeys() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public PublicKey generateKeyFromString(String key) throws Exception {
        byte[] encodedKey = Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedKey);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public static void saveKeyToFile(String fileName, BigInteger mod, BigInteger exponent) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            oos.writeObject(mod);
            oos.writeObject(exponent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }
    }

    public static Key readFromFile(String fileName, boolean isPublicKey) throws Exception {
        Key key = null;
        InputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
        try {
            BigInteger mod = (BigInteger) objectInputStream.readObject();
            BigInteger exponent = (BigInteger) objectInputStream.readObject();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (isPublicKey) {
                key = keyFactory.generatePublic(new RSAPublicKeySpec(mod, exponent));
            } else {
                key = keyFactory.generatePrivate(new RSAPrivateKeySpec(mod, exponent));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objectInputStream.close();
        }
        return key;
    }

    public void prepareAndSaveKeys(String marker, PublicKey publicKey, PrivateKey privateKey) throws Exception {
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpec = factory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpec = factory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

        saveKeyToFile(marker + "-public.key", publicKeySpec.getModulus(), publicKeySpec.getPublicExponent());
        saveKeyToFile(marker + "-private.key", privateKeySpec.getModulus(), privateKeySpec.getPrivateExponent());
    }

}
