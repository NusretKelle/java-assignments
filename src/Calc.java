import java.util.Scanner;
import java.util.Stack;

public class Calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        while (true) {
            if (!sc.hasNext()) break;
            String token = sc.next();
            if (token.equals("!")) break;
            else if (token.equals("?")) System.out.println(stack);
            else if (token.equals("^")) {
                if (stack.isEmpty()) System.err.println("# stack is empty");
                else System.out.println(stack.pop());
            } else if (isOperator(token)) {
                if (stack.size() < 2) System.err.println("# not enough operands");
                else {
                    int b = stack.pop(), a = stack.pop();
                    try { stack.push(applyOperator(a, b, token)); }
                    catch (ArithmeticException e) {
                        System.err.println("# " + e.getMessage());
                        stack.push(a); stack.push(b);
                    }
                }
            } else if (isInteger(token)) stack.push(Integer.parseInt(token));
            else System.err.println("# invalid input: " + token);
        }
        sc.close();
    }
    private static boolean isOperator(String s) {
        return s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("%");
    }
    private static int applyOperator(int a, int b, String op) {
        switch (op) {
            case "+": return a+b;
            case "-": return a-b;
            case "*": return a*b;
            case "/": if (b==0) throw new ArithmeticException("division by zero"); return a/b;
            case "%": if (b==0) throw new ArithmeticException("mod by zero"); return a%b;
        }
        throw new IllegalArgumentException("Unknown operator: " + op);
    }
    private static boolean isInteger(String s) {
        try { Integer.parseInt(s); return true; }
        catch (NumberFormatException e) { return false; }
    }
}
