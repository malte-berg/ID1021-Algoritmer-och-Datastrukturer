public class Main {
    public static void main(String[] args) {
        QueueWrap q = new QueueWrap(4);
        q.print();
        q.enqueue(1);
        q.print();
        q.enqueue(2);
        q.print();
        q.enqueue(3);
        q.print();
        System.out.println(q.dequeue());
        q.print();
        q.enqueue(4);
        q.print();
        q.enqueue(5);
        q.print();
        q.enqueue(6);
        q.print();
    }
}
