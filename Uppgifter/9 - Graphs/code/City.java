public class City {
    public String name;
    public Connection[] connections;

    public City(String name) {
        this.name = name;
        connections = new Connection[4];
    }

    public void connect(City nxt, Integer dst) {
        int i = 0;
        while (connections[i] != null) i++;
        connections[i] = new Connection(nxt, dst);
    }

    public String toString() {
        String s = name + ": [ ";
        int index = 0;
        while (index < 4 && connections[index] != null) {
            s += connections[index].toString();
            index++;
        }
        s += "]";
        return s;
    }
}
