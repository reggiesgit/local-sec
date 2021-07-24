package com.inter.challenge.cache;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 18/07/2021
 */

public class DigitCache<String, Integer> {

    private String key;
    private Integer value;
    private DigitCache previous;
    private DigitCache next;

    public DigitCache(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public DigitCache() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DigitCache getPrevious() {
        return previous;
    }

    public void setPrevious(DigitCache previous) {
        this.previous = previous;
    }

    public DigitCache getNext() {
        return next;
    }

    public void setNext(DigitCache next) {
        this.next = next;
    }
}
