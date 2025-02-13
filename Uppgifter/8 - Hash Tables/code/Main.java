public class Main {

    private static ZipArrays allZips = new ZipArrays("src/postnummer.csv", 14683*2);

    public static void main(String[] args) {


        //String[] zips = {"111 15", "984 99"};
        int[] zips = {11115, 98499};

        int k = 100;
        int loop = 1000;

        bench(13535, 10000000);

        for (int z : zips) {
            double tSum = 0;
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < k; i++) {
                double t = bench(z, loop);
                min = (t < min) ? t : min;
                tSum += t;
            }
            double avg = tSum / k;
            System.out.printf("Zip: %s\tBest: %.5f\n", z, min);
        }



        /*
        int min = Integer.MAX_VALUE;
        int bestMod = 0;

        int mod = 14683;
        for (double mul = 1; mul < 9; mul+=0.5) {
            ZipArrays allZips = new ZipArrays("src/postnummer.csv", (int)(mod * mul));
            int[] lookups = new int[5000];

            for (int i = 11111; i < 100000; i++) {
                int num = allZips.lookup(i);
                if (num != -1) {
                    lookups[num]++;
                }
            }
            int sum = 0;
            for (int i = 1; i < lookups.length; i++) {
                if (lookups[i] != 0) {
                    //System.out.println(i + ":\t" + lookups[i]);
                    sum += i * lookups[i];
                }
            }
            if (sum < min) {
                min = sum;
                //bestMod = mod*mul;
            }
            System.out.println((int)(mod*mul) + ": " + sum);
            //System.out.println("Ratio: " + (double)sum / (int)(mod*mul));
        }

         */


    }

    public static double bench(int key, int loop) {
        double tot = 0;

        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            allZips.lookup(key);
        }
        long t1 = System.nanoTime();
        tot = (double) t1 - t0;
        return tot / loop;
    }
}
