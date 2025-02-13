public class StackStatic extends Stack{
    int[] stack;
    int top = 0;

    public StackStatic(int size) {
        if (size < 1) {
            throw new IllegalArgumentException(
                    "Stack size has to be at least 1");
        }
        stack = new int[size];
    }

    @Override
    public void push(int val) {
        try {
            stack[top++] = val;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Stack full.");
            throw e;
        }
    }

    @Override
    public int pop() {
        int popped;
        try {
             popped = stack[--top];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Empty stack.");
            throw e;
        }
        return popped;
    }
}