package com.inter.challenge;

import com.inter.challenge.cache.Cache;
import com.inter.challenge.component.CacheComponent;
import com.inter.challenge.model.VerifierDigit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 18/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CacheTester {

    private CacheComponent component = new CacheComponent();
    private Cache cache = Cache.getCurrentCache();

    @Test
    @Order(1)
    public void useCache() {
        VerifierDigit pair = component.resolveAndCacheVerifier("1111");
        assertTrue(cache.getCurrentSize() == 1);
        assertTrue(pair.getDigit() == 4);
    }

    @Test
    @Order(2)
    public void checkCache() {
        assertTrue(cache.getCurrentSize() > 0);
    }

    @Test
    @Order(3)
    public void addExistingValue() {
        component.resolveAndCacheVerifier("1111");
        assertTrue(cache.getCurrentSize() == 1);
    }

    @Test
    @Order(4)
    public void explodeCache() {
        component.resolveAndCacheVerifier("0001");
        component.resolveAndCacheVerifier("0002");
        component.resolveAndCacheVerifier("0003");
        component.resolveAndCacheVerifier("0005");
        component.resolveAndCacheVerifier("0006");
        component.resolveAndCacheVerifier("0007");
        component.resolveAndCacheVerifier("0008");
        component.resolveAndCacheVerifier("0009");
        component.resolveAndCacheVerifier("0010");
        assertTrue(cache.getCurrentSize() == 10);
        component.resolveAndCacheVerifier("9999");
        assertTrue(cache.getCurrentSize() == 10);
        assertNull(cache.get("1111"));
    }
}
