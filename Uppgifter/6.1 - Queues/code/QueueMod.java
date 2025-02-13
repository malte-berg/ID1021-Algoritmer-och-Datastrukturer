public class QueueMod {
    Node head;
    Node end;

    private class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public QueueMod() {
        head = null;
        end = null;
    }

    public QueueMod(int n) {
        Node last = new Node(n, null);
        end = last;
        for (int i = n-1; i > 0; i--) {
            last = new Node(i, last);
        }
        head = last;
    }

    public void enqueue(Integer item) {
        if (head == null) {
            head = new Node(item, null);
            end = head;
        }
        else {
            end.next = new Node(item, null);
            end = end.next;
        }
    }

    public Integer dequeue() {
        if (head == null) return null;
        Integer val = head.item;
        head = head.next;
        return val;
    }
}
