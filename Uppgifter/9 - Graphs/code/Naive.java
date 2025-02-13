public class Naive {
    public static void main(String[] args) {
        Map map = new Map("src/trains.csv");
        /*
        String from = args[0] ;
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);

         */

        //System.out.println("Time: " + shortestGPT(map.lookup("Göteborg"), map.lookup("Umeå"), 800));


        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup("Göteborg"), map.lookup("Stockholm"), 300);
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");

    }

    public static Integer shortest(City from, City to, Integer max) {
        if (max < 0) return null;
        if (from == to) return 0;

        Integer shortestTime = null;

        for (int i = 0; i < from.connections.length; i++) {
            if (from.connections[i] != null) {
                Connection conn = from.connections[i];
                //System.out.println(conn.toString() + ", time left: " + max);
                //if (max - conn.time >= 0) {
                    Integer time = shortest(conn.destination, to, max - conn.time);
                    if (time != null) {
                        int totalTime = time + conn.time;
                        if (shortestTime == null || totalTime < shortestTime) {
                            shortestTime = totalTime;
                        }
                    }
               // }
            }
        }
        return shortestTime;
    }
}
