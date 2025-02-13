import java.io.*;

public class HP35 {
    public static void main(String[] args) throws IOException {
        Stack s = new StackDynamic();

        System.out.println("HP35 Calculator");
        boolean run = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (run) {
            System.out.print("> ");
            String input = br.readLine();
            int first, second;
            switch (input) {
                case "+":
                    second = s.pop();
                    first = s.pop();
                    s.push(first + second);
                    break;

                case "-":
                    second = s.pop();
                    first = s.pop();
                    s.push(first - second);
                    break;

                case "*":
                    second = s.pop();
                    first = s.pop();
                    s.push(first * second);
                    break;

                case "/":
                    second = s.pop();
                    first = s.pop();
                    s.push(first / second);
                    break;

                case "":
                    run = false;
                    break;

                default:
                    s.push(Integer.parseInt(input));
                    break;
            }
        }
        System.out.printf("The result is: %d\n\n", s.pop());
    }
}
