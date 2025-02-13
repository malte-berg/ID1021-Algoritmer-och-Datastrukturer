public class Main {
    public static void main(String[] args) {
        int size = 16;
        int[] sizes = new int[17];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = size;
            size *=2;
        }

        int k = 100;
        int loop = 10;

        bench(10000, 10000);
        bench(10000, 10000);

        for (int n : sizes) {
            double tSum = 0;
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < k; i++) {
                double t = bench(n, loop);
                min = t < min ? t : min;
                tSum += t;
            }
            double avg = tSum / k;
            System.out.printf("Size: %d\tBest: %.2f\n", n, min);
        }
    }

    public static double bench(int n, int loop) {
        double tot = 0;
        for (int i = 0; i < loop; i++) {
            //Queue q = new Queue(n);
            QueueMod q = new QueueMod(n);

            long t0 = System.nanoTime();
            q.dequeue();
            long t1 = System.nanoTime();
            tot += (double) (t1-t0);
        }
        return (double) tot/loop;
    }
}
