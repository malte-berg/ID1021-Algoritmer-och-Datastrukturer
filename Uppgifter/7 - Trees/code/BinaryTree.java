public class BinaryTree {
    private class Node {
        private Integer value;
        private Node left, right;

        private Node(Integer value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    private class StackDynamic {
        Node[] stack;

        int size = 4;
        int top = 0;

        private StackDynamic() {
            stack = new Node[size];
        }

        public void push(Node node) {
            if (top == size) {
                size *=2;
                Node[] newStack = new Node[size];
                System.arraycopy(stack, 0, newStack, 0, top);
                this.stack = newStack;
            }
            // No try-catch needed for IndexOutOfBoundsException as size increases correctly
            stack[top++] = node;
        }

        public Node pop() {
            Node popped;
            try {
                popped = stack[--top];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Empty stack.");
                throw e;
            }
            if (size > 4) {
                if (top <= size / 2 - size / 8) {
                    this.size /= 2;
                    Node[] newStack = new Node[size];
                    System.arraycopy(stack, 0, newStack, 0, top);
                    this.stack = newStack;
                }
            }
            return popped;
        }

        public boolean isEmpty() {
            return top - 1 < 0;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void add(Integer value) {
        root = recursiveAdd(root, value);
    }

    private Node recursiveAdd(Node current, Integer value) {
        if (current == null) return new Node(value);

        if (current.value == value) return current;

        if (current.value > value) current.left = recursiveAdd(current.left, value);
        else current.right = recursiveAdd(current.right, value);

        return current;
    }

    public void iterativeAdd(Integer value) {
        if (root == null) root = new Node(value);

        Node current = root;
        Node previous = null;

        while (current != null) {
            previous = current;
            if (current.value == value) return;
            if (current.value > value) current = current.left;
            else current = current.right;
        }

        if (previous.value > value) previous.left = new Node(value);
        else previous.right = new Node(value);
    }

    public boolean lookup(Integer key) {
        return recursiveLookup(root, key);
    }

    private boolean recursiveLookup(Node current, Integer key) {
        if (current == null) return false;
        if (current.value == key) return true;
        if (current.value > key) return recursiveLookup(current.left, key);
        else return recursiveLookup(current.right, key);
    }

    public void print() {
        recursivePrint(root);
    }

    private void recursivePrint(Node current) {
            if(current.left != null)
                recursivePrint(current.left);
            System.out.println(current.value);
            if(current.right != null)
                recursivePrint(current.right);
    }

    public void stackPrint() {
        StackDynamic stack = new StackDynamic();
        Node current = this.root;
        // move to the leftmost node
        while (current.left != null) {
            stack.push(current);
            current = current.left;
        }
        while (current != null) {
            // print value of node
            System.out.println(current.value);
            if (current.right != null) {
                current = current.right;
            // move to the leftmost node, push nodes as you go
                while (current.left != null) {
                    stack.push(current);
                    current = current.left;
                }
            } else if (stack.isEmpty()){
                return;
// pop a node from the stack
            } else {
                current = stack.pop();
            }
        }
// done
    }
}
