package com.inter.challenge.component;

import org.springframework.stereotype.Component;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 17/07/2021
 */

@Component
public class DigitComponent {

    public int discoverVerifier(int i) {
        int result = 0;
        while (i > 0) {
            result += i % 10;
            i = i / 10;
        }
        if (result > 9) {
            return discoverVerifier(result);
        } else {
            return result;
        }
    }

    public String mountCard(int s, int i) {
        StringBuilder card = new StringBuilder();
        for (int r = 0; i > r; r++) {
            card.append(s);
        }
        return new String(card);
    }

    public int discoverVerifier(String s) {
        char[] digits = s.toCharArray();
        int sum = 0;
        for (int i = 0; digits.length > i; i++) {
            sum += Character.getNumericValue(digits[i]);
        }
        return discoverVerifier(sum);
    }
}
