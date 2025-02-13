public class Dynamic {
    City[] path;
    int pathIndex;
    Integer maxPath;

    public Dynamic() {
        path = new City[54];
        pathIndex = 0;
    }

    private Integer shortest(City from, City to, Integer max) {
        if (from == to) return 0;

        for (int i = 0; i < pathIndex; i++)
            if (path[i] == from)
                return null;

        path[pathIndex++] = from;

        Integer shortestTime = null;

        for (int i = 0; i < from.connections.length; i++) {
            if (from.connections[i] != null) {
                Connection conn = from.connections[i];
                Integer newMax = (max == null) ? null : max - conn.time;
                if (newMax != null && newMax < 0) continue;
                //System.out.println(conn.toString() + ", time left: " + max);
                Integer time = shortest(conn.destination, to, newMax);
                if (time != null) {
                    int totalTime = time + conn.time;
                    if (shortestTime == null || totalTime < shortestTime) {
                        shortestTime = totalTime;
                        if (max == null || totalTime < max) max = totalTime;
                    }
                }
            }
        }

        path[--pathIndex] = null;
        return shortestTime;
    }


    public static void main(String[] args) {
        Map map = new Map("src/trains.csv");
        Dynamic d = new Dynamic();
        String[] cities = { "Södertälje", "Norrköping", "Linköping", "Mjölby", "Nässjö", "Alvesta", "Hässleholm", "Lund", "Göteborg", "Varberg", "Halmstad", "Åstorp", "Skövde", "Herrljunga", "Falköping", "Värnamo", "Emmaboda", "Kristianstad", "Karlskrona", "Jönköping", "Katrineholm", "Hallsberg", "Örebro", "Arboga", "Stockholm", "Uppsala", "Gävle", "Sundsvall", "Ånge", "Umeå", "Boden", "Gällivare", "Borlänge", "Mora", "Sveg", "Östersund", "Avesta", "Storvik", "Fagersta", "Frövi", "Ludvika", "Västerås", "Eskilstuna", "Strömstad", "Uddevalla", "Trollhättan", "Helsingborg", "Kalmar", "Kiruna", "Luleå", "Sala" };

        //for (String c : cities) {
            long t0 = System.nanoTime();
            Integer dist = d.shortest(map.lookup("Umeå"), map.lookup("Göteborg"), null);
            long time = (System.nanoTime() - t0) / 1_000;

            System.out.println("shortest: " + dist + " min (" + (double) time/1000 + " ms)");
        //}

    }
}
