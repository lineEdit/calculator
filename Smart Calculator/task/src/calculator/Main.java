package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner("-2 + 4 - 5 + 6");
        // put your code here
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "/exit":
                    System.out.println("Bye!");
                    return;
                case "/help":
                    System.out.println("The program calculates the sum of numbers");
                    break;
                case "":
                    continue;
                default:
                    if (input.contains("/")) {
                        System.out.println("Unknown command");
                        continue;
                    }
            }
            String clear = clearOther(input);
            if (clear == null) {
                System.out.println("Invalid expression");
            } else {
                List<String> stringList = createPostfixNotation(clear);
                if (stringList == null) {
                    System.out.println("Invalid expression");
                } else {
                    System.out.println(readPostfixNotation(stringList));
                }
            }
        }
    }

    static String clearOther(String string) {
        String out = string;
        while (out.contains("--")) {
            out = out.replaceAll("--" , "+");
        }
        while (out.contains("++")) {
            out = out.replaceAll("\\+\\+" , "+");
        }
        out = out.replaceAll("\\+", " + ");
        out = out.replaceAll("-", " - ").trim();

//Testing invalid expression
        for (String item : out.split("\\s+")) {
            if (item.matches("[a-z]+")) {
                return null;
            }
        }
//Testing invalid expression
        int digits = out.split("\\W+").length;
        int symbolMath = out.split("\\d+").length;
        if (digits < symbolMath){
            return null;
        }
        return out;
    }

    static List<String> createPostfixNotation(String string) {
        Stack<String> stack = new Stack<>();
        List<String> out = new ArrayList<>();
        for (String item : string.split("\\s+")) {
            if (item.matches("\\d+")) {
                out.add(item);
            } else {
                if (stack.size() > 0) {
                    out.add(stack.pop());
                }
                stack.push(item);
            }
        }
        if (stack.size() > 0 ) {
            out.add(stack.pop());
        }

//Testing invalid expression
        if (!(out.contains("+") || out.contains("-"))) {
            return null;
        }

        return out;
    }

    static int readPostfixNotation(List<String> list) {
        if (list == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(Integer.parseInt(item));
            } else {
                int pop = 0;
                if (stack.size() > 0) {
                    switch (item) {
                        case "+":
                            pop = stack.pop();
                            break;
                        case "-":
                            pop = -stack.pop();
                            break;
                    }
                }
                while (stack.size() > 0) {
                    pop += stack.pop();
                }
                stack.push(pop);
            }
        }
        return stack.pop();
    }
}
