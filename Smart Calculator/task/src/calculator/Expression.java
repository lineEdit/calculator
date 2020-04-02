package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression {
    private String[] expression;

    public Expression(InputLine input) {
        expression = input.getContent().split("\\s+");
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

//Testing invalid expression
        if (!(out.contains("+") || out.contains("-"))) {
            return null;
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
