public class CityLinkedList {
    public CityNode first;

    public class CityNode {
        public City head;
        public CityNode tail;

        public CityNode(City head) {
            this.head = head;
            tail = null;
        }
    }

    public CityLinkedList() {
        first = null;
    }

    public void add(City city) {
        if (first == null) first = new CityNode(city);
        else {
            CityNode current = first;
            while (current.tail != null) current = current.tail;
            current.tail = new CityNode(city);
        }
    }

    public City find(String city) {
        CityNode current = first;
        while (current != null) {
            if (current.head.name.equals(city)) return current.head;
            current = current.tail;
        }
        return null;
    }
}
