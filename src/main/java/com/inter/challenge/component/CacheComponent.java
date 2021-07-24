package com.inter.challenge.component;

import com.inter.challenge.cache.Cache;
import com.inter.challenge.cache.DigitCache;
import com.inter.challenge.model.VerifierDigit;
import org.springframework.stereotype.Component;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 18/07/2021
 */

@Component
public class CacheComponent {

    private DigitComponent digitComponent = new DigitComponent();
    private Cache currentCache = Cache.getCurrentCache();

    public VerifierDigit resolveAndCacheVerifier(String s) {
        VerifierDigit result = new VerifierDigit();
        if (containsInCache(s)) {
            DigitCache found = (DigitCache) currentCache.get(s);
            result.setDigit((Integer) found.getValue());

        } else {
            int verifier = digitComponent.discoverVerifier(s);
            addToCache(s, verifier);
            result.setDigit(verifier);
        }
        result.setParam(s);
        return result;
    }

    public boolean containsInCache(String s) {
        return currentCache.get(s) != null;
    }

    public void addToCache(String key, Integer value) {
        Cache cache = Cache.getCurrentCache();
        DigitCache calc = new DigitCache(key, value);
        cache.put(key, calc);
    }
}
