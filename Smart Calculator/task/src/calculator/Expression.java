package calculator;

import java.util.*;

public class Expression {
    private String[] expression;
    private List<String> postfix;
    Assignment assignment;

    public Expression(InputLine input, Assignment assignment) {
        expression = input.getContent().split("\\s+");
        this.assignment = assignment;
        this.postfix = new ArrayList<>();
    }

    public void calc() {
        if (expression.length == 1) {
            if (expression[0].matches("\\s*[a-zA-Z]+")) {
                Integer integer = assignment.getValue(expression[0]);
                if (integer == null) {
//                    Use print, otherwise the test fails
                    System.out.print("Unknown variable\n");
                } else {
                    System.out.println(integer);
                }
            } else {
                System.out.println(Integer.parseInt(expression[0]));
            }
        } else {
            setValuesToTeplaceVariables();

            createPostfixNotation();

            System.out.println(calcPostfixNotation());
        }
    }

    private void setValuesToTeplaceVariables() {
        for(int i = 0; i < expression.length; ++i) {
            if (expression[i].matches("[a-zA-Z]+")) {
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
        Deque<String> stack = new ArrayDeque<>();
        for (String item : expression) {
            if (item.contains("(")) {
                stack.push(item);
            } else if (item.contains(")")) {
                boolean flagNotExist = true;
                while (stack.size() > 0) {
                    String value = stack.pop();
                    if (value.contains("(")) {
                        flagNotExist = false;
                        break;
                    }
                    postfix.add(value);
                }
                if (flagNotExist) {
                    System.out.println("Invalid expression");
                    return;
                }
            } else if (item.matches("\\d+")) {
                    postfix.add(item);
            } else if (item.contains("+")
                    || item.contains("-")
                    || item.contains("*")
                    || item.contains("/")) {
                if (stack.size() > 0
                        && (item.contains("+")
                            || item.contains("-")
                            || stack.peek().contains("+")
                            || stack.peek().contains("-")
                        ) && (stack.peek().contains("*")
                                || stack.peek().contains("/")
                        )
                ) {
                    postfix.add(stack.pop());
                    stack.push(item);
                } else {
                    stack.push(item);
                }
            }
//            } else if (
//                    (item.contains("+")
//                            || item.contains("-")
//                            || stack.peek().contains("+")
//                            || stack.peek().contains("-")
//                    ) && (stack.peek().contains("*")
//                            || stack.peek().contains("/")
//                    )
//            ) {
//                postfix.add(stack.pop());
//                stack.push(item);
//            }

        }
        while (stack.size() > 0 ) {
            postfix.add(stack.pop());
        }
    }

    private int calcPostfixNotation() {
        Deque<Integer> integerStack = new ArrayDeque<>();
        for (String item : postfix) {
            if (item.matches("\\d+")) {
                integerStack.push(Integer.parseInt(item));
            } else {
                int pop = 0;
                if (integerStack.size() >= 2) {
                    switch (item) {
                        case "+":
                            pop = integerStack.pop() + integerStack.pop();
                            break;
                        case "-":
                            pop = -integerStack.pop() + integerStack.pop();
                            break;
                        case "*":
                            pop = integerStack.pop() * integerStack.pop();
                            break;
                    }
                }
//                if (integerStack.size() > 0) {
//                    switch (item) {
//                        case "+":
//                            pop += integerStack.pop();
//                            break;
//                        case "*":
//                            pop *= integerStack.pop();
//                            break;
//                    }
//                }
//                while (integerStack.size() > 0) {
//                    switch (item) {
//                        case "+":
//                            pop += integerStack.pop();
//                            break;
//                        case "*":
//                            pop *= integerStack.pop();
//                            break;
//                    }
//                }
                integerStack.push(pop);
            }
        }
        return integerStack.pop();
    }
}
