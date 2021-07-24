package com.inter.challenge.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 18/07/2021
 */

public class Cache<String, Integer> {

    private final int MAX_SIZE = 10;
    private static Cache currentCache;
    private Map<String, DigitCache> map;
    private int currentSize;
    private DigitCache first;
    private DigitCache last;

    public Cache() {
        map = new HashMap<>(MAX_SIZE);
    }

    public static Cache getCurrentCache() {
        if (currentCache == null) {
            currentCache = new Cache();
        }
        return currentCache;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void delete(String key) {
        deleteNode(map.get(key));
    }

    public void put(String key, Integer value) {
        DigitCache node = new DigitCache(key, value);
        if (!map.containsKey(key)) {
            if (getCurrentSize() >= MAX_SIZE) {
                deleteNode(first);
            }
            addNode(node);
        }
        map.put(key, node);
    }

    private void deleteNode(DigitCache node) {
        if (node != null) {
            if (node == last) {
                last = node.getPrevious();
            }
            if (node == first) {
                first = node.getNext();
            }
            map.remove(node.getKey());
            currentSize--;
        }
    }

    private void addNode(DigitCache node) {
        if (last != null) {
            node.setPrevious(last);
            last.setNext(node);
        }
        last = node;
        if (first == null) {
            first = node;
        }
        currentSize++;
    }

    public Integer get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        DigitCache node = (DigitCache) map.get(key);
        return (Integer) node.getValue();
    }
}
