package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression {
    private String[] expression;
    Assignment assignment;

    public Expression(InputLine input, Assignment assignment) {
        expression = input.getContent().split("\\s+");
        this.assignment = assignment;
    }

    public int calc() {
        if (expression.length == 1) {
            return Integer.parseInt(expression[0]);
        }
        return 0;
    }

    String clearOther(String string) {
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

    List<String> createPostfixNotation(String string) {
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

    int readPostfixNotation(List<String> list) {
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
