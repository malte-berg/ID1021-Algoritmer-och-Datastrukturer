public class QueueWrap {
    Integer[] queueArr; // the queue
    int maxSize;        // n
    int firstItem;      // i
    int firstFree;      // k
    int length;         // current size

    public QueueWrap(int maxSize) {
        this.maxSize = maxSize;
        queueArr = new Integer[maxSize];
        firstItem = 0;
        firstFree = 0;
        length = 0;
    }

    public void enqueue(Integer item) {
        if (length == maxSize) increaseSize();
        queueArr[firstFree] = item;
        firstFree = increment(firstFree);
        length++;
    }

    public Integer dequeue() {
        if (length == 0) return null;
        Integer dequeued = queueArr[firstItem];
        queueArr[firstItem] = null;
        firstItem = increment(firstItem);
        length--;
        return dequeued;
    }

    private void increaseSize() {
        int newMaxSize = maxSize * 2;

        Integer[] increasedQueueArr = new Integer[newMaxSize];
        for (int i = 0; i < length; i++)
            increasedQueueArr[i] = queueArr[(firstItem + i) % maxSize];

        firstItem = 0;
        firstFree = length;
        queueArr = increasedQueueArr;
        maxSize = newMaxSize;
    }

    private int increment(int toIncrement) {
        return (toIncrement + 1) % maxSize;
    }

    public void print() {
        String s = "[ ";
        for (int i = 0; i < maxSize-1; i++) {
            if (queueArr[i] == null) s += "null, ";
            else {
                s = s + String.valueOf(queueArr[i]) + ", ";
            }
        }
        if (queueArr[maxSize - 1] == null) s += "null ]";
        else {
            s = s + String.valueOf(queueArr[maxSize - 1]) + " ]";
        }
        System.out.println(s);
    }

}
