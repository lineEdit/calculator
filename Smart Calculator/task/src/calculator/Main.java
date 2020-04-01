package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            InputLine input = new InputLine(scanner.nextLine());

            if (input.isCommand()) {
                Command command = new Command(input);
                command.action();
            } else if (input.isExpression()) {

            } else if (!input.isEmpty()) {
                System.out.println("Invalid expression");
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
