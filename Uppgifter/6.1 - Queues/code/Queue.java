public class Queue {
    Node head;

    private class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public Queue() {
        head = null;
    }

    public Queue(int n) {
        Node last = new Node(n, null);
        for (int i = n-1; i > 0; i--) {
            last = new Node(i, last);
        }
        head = last;
    }

    public void enqueue(Integer item) {
        if (head == null) head = new Node(item, null);
        else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = new Node(item, null);
        }
    }

    public Integer dequeue() {
        if (head == null) return null;
        Integer val = head.item;
        head = head.next;
        return val;
    }
}
