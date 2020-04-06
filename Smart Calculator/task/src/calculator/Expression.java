package calculator;

import java.math.BigInteger;
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
                BigInteger integer = assignment.getValue(expression[0]);
                if (integer == null) {
//                    Use print, otherwise the test fails
                    System.out.print("Unknown variable\n");
                } else {
                    System.out.println(integer);
                }
            } else {
                System.out.println(new BigInteger(expression[0]));
            }
        } else {
            setValuesToTeplaceVariables();

            createPostfixNotation();

            if (postfix != null) {
                System.out.println(calcPostfixNotation());
            }
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

    private int prior(String item) {
        switch (item) {
            case "(": return 0;
            case ")": return 0;
            case "+": return 1;
            case "-": return 1;
            case "*": return 2;
            case "/": return 2;
            case "^": return 3;
        }
        return -1;
    }

    private void createPostfixNotation() {
        int leftC = 0; //Count (
        int rightC = 0;//Count )
        Deque<String> stack = new ArrayDeque<>();
        for (String item : expression) {
            if (item.contains("(")) {
                ++leftC;
            }
            if (item.contains(")")) {
                ++rightC;
            }
            if (item.matches("\\d+")) {
                postfix.add(item);
            } else if (item.contains("(")) {
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
                    postfix = null;
                    System.out.println("Invalid expression");
                    return;
                }
            } else if (item.contains("+")
                    || item.contains("-")
                    || item.contains("*")
                    || item.contains("/")) {
//                if (stack.size() > 0) {
                    while (stack.size() > 0) {
                        if (prior(item) <= prior(stack.peek()) && prior(stack.peek()) > 0) {
                            postfix.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(item);
//                } else {
//                    stack.push(item);
//                }
            }
        }
        while (stack.size() > 0 ) {
            postfix.add(stack.pop());
        }
        if (leftC != rightC) {
            postfix = null;
            System.out.println("Invalid expression");
        }
    }

    private BigInteger calcPostfixNotation() {
        Deque<BigInteger> integerStack = new ArrayDeque<>();
        for (String item : postfix) {
            if (item.matches("\\d+")) {
                integerStack.push(new BigInteger(item));
            } else {
                BigInteger pop = BigInteger.ZERO;
                if (integerStack.size() >= 2) {
                    switch (item) {
                        case "+":
                            pop = integerStack.pop().add(integerStack.pop());
                            break;
                        case "-":
                            pop = integerStack.pop().negate().add(integerStack.pop());
                            break;
                        case "*":
                            pop = integerStack.pop().multiply(integerStack.pop());
                            break;
                        case "/":
                            BigInteger left = integerStack.pop();
                            BigInteger right = integerStack.pop();
                            pop = right.divide(left);
                            break;
                    }
                }
                integerStack.push(pop);
            }
        }
        return integerStack.pop();
    }
}
