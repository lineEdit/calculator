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
            if (input.contains("/exit")) {
                System.out.println("Bye!");
                break;
            }
            if (input.contains("/help")) {
                System.out.println("The program calculates the sum of numbers");
            }
            if (input.isEmpty()) {
                continue;
            }

            System.out.println(
                    readPostfixNotation(
                        createPostfixNotation(
                                clearOther(input)
                        )
                    )
            );


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
        return out;
    }

    static int readPostfixNotation(List<String> list) {
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
