import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
    private CityLinkedList[] cities;
    private final int mod = 61;

    public Map(String file) {
        cities = new CityLinkedList[mod];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                City one = lookup(row[0]);
                City two = lookup(row[1]);

                one.connect(two, Integer.valueOf(row[2]));
                two.connect(one, Integer.valueOf(row[2]));
            }
        } catch (Exception e) {
            System.out.println("File " + file + " not found.");
            System.out.println(e);
        }
    }

    public City lookup(String city) {
        int hashed = HashUtil.hash(city, mod);
        CityLinkedList bucket = cities[hashed];
        if (bucket == null) {
            bucket = new CityLinkedList();
            cities[hashed] = bucket;
        }

        City cityToAdd = bucket.find(city);
        if (cityToAdd != null) return cityToAdd;
        else {
            cityToAdd = new City(city);
            cities[hashed].add(cityToAdd);
            return cityToAdd;
        }
    }
}
