import java.util.Random;

public class Main {
    public static void main(String[] args) {
        bench(10000, 50);

        int[] sizes = new int[23];
        int val = 16;
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = val;
            val*=2;
        }

        int k = 1;
        int loop = 2;

        for (int n : sizes) {
            double tSum = 0;
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < k; i++) {
                double t = bench(n, loop);
                min = (t < min) ? t : min;
                tSum += t;
            }
            double avg = tSum / k;
            System.out.printf("Size: %d\tBest: %.2f\n", n, min);
        }

        /*
        int[] mergeArr = unsorted(12);
        System.out.print("[ ");
        for (int n : mergeArr) System.out.print(n + " ");
        System.out.print("]\n");

        mergesort(mergeArr);

        System.out.print("[ ");
        for (int n : mergeArr) System.out.print(n + " ");
        System.out.print("]\n");
        */
    }
/*
    public static void mergesort(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length -1);
    }

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = (lo + hi)/2;
            // sort the items from lo to mid
            sort(org, aux, lo, mid);
            // sort the items from mid+1 to hi
            sort(org, aux, mid+1, hi);
            // merge the two sections using the additional array
            merge(org, aux, lo, mid, hi);
        }
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
// copy all items from lo to hi from org to aux
        for (int i = lo; i <= hi; i++) {
            aux[i] = org[i];
        }
// let's do the merging
        int i = lo; // the index in the first part
        int j = mid+1; // the index in the second part
// for all indices from lo to hi
        for (int k = lo; k <= hi; k++) {
            // if i is greater than mid then
            // move the j'th item to the org array, update j
            if (i > mid) org[k] = aux[j++];
            // else if j is greate than hi then
            // move the i'th item to the org array, update i
            else if (j > hi) org[k] = aux[i++];
            // else if the i'th item is smaller or equal to the j'th item,
            // move the i'th item to the org array, update i
            else if (aux[i] <= aux[j]) org[k] = aux[i++];
            // else
            // move the j'th item to the org array, update j
            else org[k] = aux[j++];
        }
    }
*/

    public static void mergesort(int[] org) {
        if (org.length == 0)
            return;
        int[] aux = org.clone();
        sort(aux, org, 0, org.length - 1);
    }

    private static void sort(int[] src, int[] dest, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        sort(dest, src, lo, mid);
        sort(dest, src, mid + 1, hi);
        merge(src, dest, lo, mid, hi);
    }

    private static void merge(int[] src, int[] dest, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dest[k] = src[j++];
            } else if (j > hi) {
                dest[k] = src[i++];
            } else if (src[i] <= src[j]) {
                dest[k] = src[i++];
            } else {
                dest[k] = src[j++];
            }
        }
    }

    private static boolean selection(int[] arr) {
        for (int i = 0; i < arr.length -1; i++) {
            int candidate = i;
            for (int j = i; j < arr.length ; j++) {
                if (arr[j] < arr[candidate]) candidate = j;
            }
            int temp = arr[i];
            arr[i] = arr[candidate];
            arr[candidate] = temp;
        }
        return true;
    }

    private static boolean insertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j-1] ; j--) {
                swap(arr, j, j-1);
            }
        }
        return true;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        int nxt = 0;
        for (int i = 0; i < n ; i++) {
            nxt += rnd.nextInt(10) + 1;
            arr[i] = nxt;
        }
        return arr;
    }

    private static int[] unsorted(int n) {
        int[] arr = sorted(n);
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    public static boolean run(int[] arr, int loop) {
        boolean bool = false;
        for (int i = 0; i < loop; i++) {
            mergesort(arr);
        }
        return bool;
    }

    public static double bench(int n, int loop) {
        Random rand = new Random();
        int[] arr = unsorted(n);
        long t0 = System.nanoTime();
        run(arr, loop);
        long t1 = System.nanoTime();
        return (double) (t1-t0)/loop;
    }
}
