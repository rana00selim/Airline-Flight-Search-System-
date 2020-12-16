package heap;

import java.util.Random;

public class heapSort {

    private static int elementCompare = 0;
    private static int elementSwaps = 0;

    public static void sort(int[] arIn) {
        int N = arIn.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(arIn, k, N);
        }
        System.out.println("Heapified: ");
        print(arIn);
        for (int k = arIn.length; k > 1; ) {
            swap(arIn, 1, k--);
            sink(arIn, 1, k);
        }
    }

    public static void sink(int[] ar, int k, int h) {
        while ((2 * k) <= h) {
            int mx = 2 * k;
            elementCompare++;
            if (mx < h && ar[mx - 1] < ar[mx]) {
                mx++;
            }
            elementCompare++;
            if (ar[k - 1] >= ar[mx - 1]) {
                break;
            }
            swap(ar, k, mx);
            k = mx;
        }
    }

    public static void swap(int[] ar, int i, int j) { //exch
        elementSwaps++;
        int tmp = ar[i - 1];
        ar[i - 1] = ar[j - 1];
        ar[j - 1] = tmp;
    }

    public static void print(int[] ar) {
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        int[] randomArray = new int[1024];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rnd.nextInt(1024);
        }
        print(randomArray);
        sort(randomArray);
        print(randomArray);
        System.out.println("Element Compare Count = " + elementCompare);
        System.out.println("Element Swap Count = " + elementSwaps);

        System.out.println("---------");
        int[] a = {2, 5, 60, 50, 11, 12, 6, 7, 23, 9, 10, 35};
        print(a);
        sort(a);
        print(a);
        System.out.println("Element Compare Count = " + elementCompare);
        System.out.println("Element Swap Count = " + elementSwaps);
    }
}