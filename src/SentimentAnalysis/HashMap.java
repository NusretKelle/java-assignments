package SentimentAnalysis;

import java.util.LinkedList;

public class HashMap<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int size;
    private double loadFactorThreshold = 0.5;

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    public HashMap() {
        this(16);
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public V get(K key) {
        int index = getIndex(key);
        for (Entry e : buckets[index]) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        for (Entry e : buckets[index]) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        buckets[index].add(new Entry(key, value));
        size++;

        if ((double) size / buckets.length > loadFactorThreshold) {
            rehash();
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<Entry>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;

        for (LinkedList<Entry> bucket : oldBuckets) {
            for (Entry e : bucket) {
                put(e.key, e.value);
            }
        }
    }
}
