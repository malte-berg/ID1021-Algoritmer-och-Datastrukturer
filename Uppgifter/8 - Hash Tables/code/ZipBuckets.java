import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class ZipBuckets {

    int mod = 14683;

    LinkidList[] buckets;

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

    class LinkidList {
        private Node head;

        public void add(Area data) {
            if (head == null) {
                head = new Node(data);
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new Node(data);
            }
        }

        public Node getHead() {
            return head;
        }

        static class Node {
            private Area data;
            private Node next;

            public Node(Area data) {
                this.data = data;
                this.next = null;
            }

            public Area getData() {
                return data;
            }

            public Node getNext() {
                return next;
            }
        }
    }

    public ZipBuckets(String file) {

        buckets = new LinkidList[mod];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s","")); // changed
                addToBucket(new Area(code, row[1].trim(), Integer.valueOf(row[2]))); // changed
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private int hash(int key) {
        return key % mod;
    }

    private void addToBucket(Area area) {
        int hashed = hash(area.getZipCode());
        if (buckets[hashed] == null) buckets[hashed] = new LinkidList();
        buckets[hashed].add(area);
    }

    public Area lookup(int key) {
        int hashed = hash(key);
        if (buckets[hashed] == null) return null;
        LinkidList.Node current = buckets[hashed].head;
        while (current != null) {
            if (current.getData().getZipCode() == key) return current.getData();
            current = current.getNext();
        }
        return null;
    }

}
