public class LinkedList {
    Cell first;

    private class Cell {
        int head;
        Cell tail;

        Cell(int val, Cell t1) {
            head = val;
            tail = t1;
        }
    }

    public LinkedList() {
        first = null;
    }

    public LinkedList(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }

    public void add (int item) {
        first = new Cell(item, first);
    }

    public int length() {
        int l = 0;
        Cell current = first;
        while (current != null) {
            l++;
            current = current.tail;
        }
        return l;
    }

    public boolean find(int item) {
        Cell current = first;
        while (current != null) {
            if (current.head == item) return true;
            current = current.tail;
        }
        return false;
    }

    public void remove(int item) {
        if (first == null) return;
        if (first.head == item) {
            first = first.tail;
            return;
        }
        Cell current = first;
        while (current.tail != null && current.tail.head != item) {
            current = current.tail;
        }

        if (current.tail != null) {
            current.tail = current.tail.tail;
        }
    }

    public void append(LinkedList b) {
        Cell nxt = first;
        while (nxt.tail != null) nxt = nxt.tail;
        nxt.tail = b.first;
        b.first = null;
    }
}
