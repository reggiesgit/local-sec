package com.inter.challenge;

import com.inter.challenge.component.DigitComponent;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 13/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VerifierTester {

    private DigitComponent component = new DigitComponent();

    @Test
    @Order(1)
    public void findItself() {
        int verifier = component.discoverVerifier(1);
        assertTrue(verifier == 1);
    }

    @Test
    @Order(2)
    public void findUniqueVerifier() {
        int verifier = component.discoverVerifier(1111);
        assertTrue(verifier == 4);
    }

    @Test
    @Order(3)
    public void findRecursiveVerifier() {
        int verifier = component.discoverVerifier(9875);
        assertTrue(verifier == 2);
    }

    @Test
    @Order(4)
    public void findFromTen() {
        int verifier = component.discoverVerifier(10);
        assertTrue(verifier == 1);
    }

    @Test
    @Order(5)
    public void findZero() {
        int verifier = component.discoverVerifier(0);
        assertTrue(verifier == 0);
    }

    @Test
    @Order(6)
    public void findFromString() {
        int verifier = component.discoverVerifier("1");
        assertTrue(verifier == 1);
    }

    @Test
    @Order(7)
    public void mountSimpleCard() {
        String card = component.mountCard(1234, 1);
        assertTrue(card.equals("1234"));
    }

    @Test
    @Order(8)
    public void mountTheirCard() {
        String card = component.mountCard(9875, 4);
        assertTrue(card.equals("9875987598759875"));
    }

    @Test
    @Order(9)
    public void mountCardAndFindVerifier() {
        String card = component.mountCard(9875, 4);
        assertTrue(card.equals("9875987598759875"));
        int verifier = component.discoverVerifier(card);
        assertTrue(verifier == 8);
    }
}
