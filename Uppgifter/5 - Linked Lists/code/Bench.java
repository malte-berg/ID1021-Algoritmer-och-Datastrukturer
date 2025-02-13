import java.util.Random;

public class Bench {
    public static void main(String[] args) {
        int[] sizes = new int[18];
        int val = 16;
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = val;
            val*=2;
        }

        bench(1000, 1000, 100000);

        int k = 10;
        int loop = 2;
        int a = 64;
        //int b = 16;

        for (int b : sizes) {
            double tSum = 0;
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < k; i++) {
                double t = bench(a, b, loop);
                min = (t < min) ? t : min;
                tSum += t;
            }
            double avg = tSum / k;
            System.out.printf("Size: %d\tBest: %.2f\n", b, min);
        }

    }

    public static boolean run(LinkedList list1, LinkedList list2, int loop) {
        boolean bool = false;
        for (int i = 0; i < loop; i++) {
            list1.append(list2);
        }
        return bool;
    }

    public static double bench(int a, int b, int loop) {
        double tot = 0;
        for (int i = 0; i < loop; i++) {
            LinkedList list1 = new LinkedList(a);
            LinkedList list2 = new LinkedList(b);

            long t0 = System.nanoTime();
            list2.append(list1);
            long t1 = System.nanoTime();
            tot += (double) (t1-t0);
        }
        return (double) tot/loop;
    }
}
