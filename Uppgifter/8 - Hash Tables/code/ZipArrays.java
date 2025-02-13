import java.io.BufferedReader;
import java.io.FileReader;

public class ZipArrays {

    int mod;

    Area[] zips;

    public class Area {
        private String areaName;
        private Integer zipCode, population;

        public Area(Integer zip, String name, Integer pop) {
            zipCode = zip;
            areaName = name;
            population = pop;
        }

        public Integer getZipCode() {
            return zipCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public int getPopulation() {
            return population;
        }

        public String toString() {
            return getZipCode() + ", " + getAreaName() + ". Pop: " + getPopulation();
        }
    }

    public ZipArrays(String file, int mod) {
        this.mod = mod;
        zips = new Area[mod];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s","")); // changed
                addToArray(new Area(code, row[1].trim(), Integer.valueOf(row[2]))); // changed
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(" file " + file + " not found");
        }
    }

    private int hash(int key) {
        return key % mod;
    }

    public void addToArray(Area area) {
        int hashed = hash(area.getZipCode());

        while (zips[hashed] != null) hashed++;
        zips[hashed] = area;
    }

    public int lookup(int key) {
        int hashed = hash(key);
        if (zips[hashed] == null) return -1;

        int lookups = 1;

        while (hashed < zips.length) {
            if (zips[hashed] != null) {
                if (zips[hashed].getZipCode() == key) return lookups;
            }
            hashed++;
            lookups++;
        }
        return -1;
    }
}
