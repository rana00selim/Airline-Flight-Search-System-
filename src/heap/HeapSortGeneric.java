package heap;

public class HeapSortGeneric {
    public static void sort(Comparable[] arIn) {
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

    public static void sink(Comparable[] ar, int k, int h) {
        while ((2 * k) <= h) {
            int mx = 2 * k;
            if (mx < h && less(ar,mx,mx+1)) {
                mx++;
            }
            if (!less(ar,k,mx)) {
                break;
            }
            swap(ar, k, mx);
            k = mx;
        }
    }

    public static boolean less(Comparable[] pq,int i,int j){
        return (pq[i-1].compareTo(pq[j-1])) < 0 ;
    }

    public static void swap(Object[] ar, int i, int j) {
        Object tmp = ar[i - 1];
        ar[i - 1] = ar[j - 1];
        ar[j - 1] = tmp;
    }

    public static void print(Comparable[] ar) {
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] a ={"s","o","r","t","e","x","a","m","p","l","e"};
        for (int i= 0; i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();;
        sort(a);
        print(a);
    }
}