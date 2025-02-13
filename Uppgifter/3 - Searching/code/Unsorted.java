import java.util.Random;
public class Unsorted {
    static double calls = 1;
    public static void main(String[] args) {
        Random rand = new Random();

        bench(1000, 10);

        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000, 10000000, 64000000};

        int loop = 10;
        int k = 100;
        for (int n : sizes) {
            double timeSum = 0;
            double worst = Long.MIN_VALUE;
            double best = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                double t = bench(n, loop);
                if (t < best) best = t;
                if (t > worst) worst = t;
                timeSum += t;
            }
            double avg = timeSum/k;
            System.out.printf("Size: %d\tBest: %.2f\tWorst: %.2f\tAvg: %.2f\n", n, best, worst, avg);
            System.out.println("steps: " + (calls/k)/loop);
            calls = 0;
        }
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

    public static int[] unsorted(int n) {
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

    public static boolean unsorted_search(int[] arr, int key) {
        for (int k : arr) {
            if (k == key) return true;
        }
        return false;
    }

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;
        while (true) {
            int index = (first + last) / 2;
            if (array[index] == key) {
                return true;
            }
            if (array[index] < key && index < last) {
                first = index + 1;
                continue;
            }
            if (array[index] > key && index > first) {
                last = index - 1;
                continue;
            }
            return false;
        }
    }

    private static boolean recursive(int[] arr, int key, int min, int max) {
        int mid = min + ((max - min)/2);
        if (arr[mid] == key) {
            return true;
// simple
        }
        if ((arr[mid] > key) && (min < mid)) {
// call recursive but narrow the search
            recursive(arr, key, min, mid-1);
            calls++;
        }
        if ((arr[mid] < key) && (mid < max)) {
// call recursive but narrow the search
            recursive(arr, key, mid+1, max);
            calls++;
        }
        return false;
// as before
    }

    public static boolean run(int[] arr, int[] keys) {
        boolean bool = false;
        for (int key: keys) {
            bool = recursive(arr, key, 0, arr.length-1);
        }
        return bool;
    }

    public static double bench(int n, int loop) {
        Random rand = new Random();
        int[] arr = sorted(n);
        int[] keys = new int[loop];
        for (int i = 0; i < loop; i++) keys[i] =  rand.nextInt(n) + 1;
        long t0 = System.nanoTime();
        run(arr, keys);
        long t1 = System.nanoTime();
        return (double) (t1-t0)/loop;
    }
}
