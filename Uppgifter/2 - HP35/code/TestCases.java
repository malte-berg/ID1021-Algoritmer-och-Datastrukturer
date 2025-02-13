public class TestCases {
    public static void main(String[] args) {
        StackStatic s1 = new StackStatic(8);
        for (int i = 0; i < 8; i++) {
            s1.push(i);
        }

        StackDynamic s2 = new StackDynamic();
        for (int i = 0; i < 32; i++) {
            s2.push(i);
        }

        while (true) {
            System.out.printf("pop: %d\ttop: %d\tsize: %d\n", s2.pop(), s2.top, s2.size);
        }
    }
}
