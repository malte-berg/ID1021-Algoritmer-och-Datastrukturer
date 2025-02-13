public class Paths {
    City[] path;
    int pathIndex;

    public Paths() {
        path = new City[54];
        pathIndex = 0;
    }

    private Integer shortest(City from, City to) {
        if (from == to) return 0;

        for (int i = 0; i < pathIndex; i++)
            if (path[i] == from)
                return null;

        path[pathIndex++] = from;

        Integer shortestTime = null;

        for (int i = 0; i < from.connections.length; i++) {
            if (from.connections[i] != null) {
                Connection conn = from.connections[i];
                Integer time = shortest(conn.destination, to);
                if (time != null) {
                    int totalTime = time + conn.time;
                    if (shortestTime == null || totalTime < shortestTime) {
                        shortestTime = totalTime;
                    }
                }
            }
        }

        path[pathIndex--] = null;
        return shortestTime;
    }


    public static void main(String[] args) {
        Map map = new Map("src/trains.csv");
        Paths p = new Paths();

        long t0 = System.nanoTime();
        Integer dist = p.shortest(map.lookup("Malmö"), map.lookup("Kiruna"));
        long time = (System.nanoTime() - t0) / 1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}
