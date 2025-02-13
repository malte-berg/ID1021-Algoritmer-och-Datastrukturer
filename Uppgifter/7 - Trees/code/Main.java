import java.util.Random;
public class Main {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        Random r = new Random();

        for (int i = 0; i < 20; i++) {
            tree.iterativeAdd(r.nextInt(100));
        }

        tree.print();
        tree.stackPrint();

        for(int i = 0; i < 40; i++) {
            int key = r.nextInt(100);
            System.out.println(key + ": " + tree.lookup(key));
        }



        int[] sizes = new int[22];
        int val = 16;
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = val;
            val*=2;
        }

        bench(1000, 100000);

        int k = 10;
        int loop = 1000;

        for (int size : sizes) {
            double tSum = 0;
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < k; i++) {
                double t = bench(size, loop);
                min = (t < min) ? t : min;
                tSum += t;
            }
            double avg = tSum / k;
            System.out.printf("Size: %d\tBest: %.2f\n", size, min);
        }
    }

    public static double bench(int size, int loop) {
        Random r = new Random();
        double tot = 0;

        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < size; i++)
            tree.add(r.nextInt(size*2));

        int[] keys = new int[loop];
        for(int i = 0; i < loop; i++)
            keys[i] = r.nextInt(size*2);

        long t0 = System.nanoTime();
        for (int n : keys) {
            tree.lookup(n);
            //tot += (double) (t1-t0);
        }
        long t1 = System.nanoTime();
        //return (double) tot/loop;
        return (double) (t1-t0)/loop;
    }
}
