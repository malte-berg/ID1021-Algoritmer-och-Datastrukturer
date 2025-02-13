public class StackDynamic extends Stack {
    int[] stack;

    int size = 4;
    int top = 0;

    public StackDynamic() {
        stack = new int[size];
    }

    @Override
    public void push(int val) {
        if (top == size) {
            size *=2;
            int[] newStack = new int[size];
            System.arraycopy(stack, 0, newStack, 0, top);
            this.stack = newStack;
        }
        // No try-catch needed for IndexOutOfBoundsException as size increases correctly
        stack[top++] = val;
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
        if (size > 4) {
            if (top <= size / 2 - size / 8) {
                this.size /= 2;
                int[] newStack = new int[size];
                System.arraycopy(stack, 0, newStack, 0, top);
                this.stack = newStack;
            }
        }
        return popped;
    }
}
