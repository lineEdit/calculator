package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression {
    private String[] expression;
    private List<String> postfix;
    Assignment assignment;

    public Expression(InputLine input, Assignment assignment) {
        expression = input.getContent().split("\\s+");
        this.assignment = assignment;
        this.postfix = new ArrayList<>();
    }

    public int calc() {
        if (expression.length == 1) {
            return Integer.parseInt(expression[0]);
        }

        setValuesToTeplaceVariables();

        createPostfixNotation();

        return calcPostfixNotation();
    }

    private void setValuesToTeplaceVariables() {
        for(int i = 0; i < expression.length; ++i) {
            if (expression[i].matches("[a-zA-Z]")) {
                for (String key : assignment.getMap().keySet()) {
                    if (expression[i].compareToIgnoreCase(key) == 0) {
                        expression[i] = assignment.getValue(key).toString();
                        break;
                    }
                }
            }
        }
    }

    private void createPostfixNotation() {
        Stack<String> stack = new Stack<>();
        for (String item : expression) {
            if (item.matches("\\d+")) {
                postfix.add(item);
            } else {
                if (stack.size() > 0) {
                    postfix.add(stack.pop());
                }
                stack.push(item);
            }
        }
        if (stack.size() > 0 ) {
            postfix.add(stack.pop());
        }
    }

    private int calcPostfixNotation() {
        Stack<Integer> integerStack = new Stack<>();
        for (String item : postfix) {
            if (item.matches("\\d+")) {
                integerStack.push(Integer.parseInt(item));
            } else {
                int pop = 0;
                if (integerStack.size() > 0) {
                    switch (item) {
                        case "+":
                            pop = integerStack.pop();
                            break;
                        case "-":
                            pop = -integerStack.pop();
                            break;
                    }
                }
                while (integerStack.size() > 0) {
                    pop += integerStack.pop();
                }
                integerStack.push(pop);
            }
        }
        return integerStack.pop();
    }
}
