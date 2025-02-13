public class Connection {
    public City destination;
    public Integer time;

    public Connection(City destination, Integer time) {
        this.destination = destination;
        this.time = time;
    }

    public String toString() {
        return destination.name + ": " + time.toString();
    }
}
