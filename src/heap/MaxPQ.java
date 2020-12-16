package heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxPQ<Key> {
    private Key[] pq;
    private int n;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Object[capacity + 1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return pq[1];
    }

    public boolean less(int i, int j) {
        return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
    }

    public void swap(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void insert(Key x) {
        pq[++n] = x;
        swim(n);
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Key max = pq[1];
        swap(1, n--);
        sink(1);
        pq[n + 1] = null;
        return max;
    }

    @Override
    public String toString() {
        return "MaxPQ{" +
                "pq=" + Arrays.toString(pq) +
                ", n=" + n +
                '}';
    }

    public static void main(String[] args) {
        MaxPQ<String> maxPQ = new MaxPQ<>(11);
        maxPQ.insert("T");
        maxPQ.insert("P");
        maxPQ.insert("R");
        maxPQ.insert("N");
        maxPQ.insert("H");
        maxPQ.insert("O");
        maxPQ.insert("A");
        maxPQ.insert("E");
        maxPQ.insert("I");
        maxPQ.insert("G");
        maxPQ.insert("S");
        System.out.println(maxPQ.toString());
    }
}