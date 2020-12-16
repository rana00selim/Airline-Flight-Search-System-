package heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinPQ<Key> {
    private Key[] pq;
    private int n;

    public MinPQ(int capacity) {
        pq = (Key[]) new Object[capacity + 1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return pq[1];
    }

    public void swap(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public boolean greater(int i, int j) {
        return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
    }

    public void insert(Key x) {
        pq[++n] = x;
        swim(n);
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public Key deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Key min = pq[1];
        swap(1, n--);
        sink(1);
        pq[n + 1] = null;
        return min;
    }

    @Override
    public String toString() {
        return "MinPQ{" +
                "pq=" + Arrays.toString(pq) +
                ", n=" + n +
                '}';
    }

    public static void main(String[] args) {
        MinPQ<Integer> minPQ = new MinPQ<>(5);
        minPQ.insert(5);
        minPQ.insert(15);
        minPQ.insert(25);
        minPQ.insert(10);
        minPQ.insert(1);
        System.out.println(minPQ.toString());
    }
}