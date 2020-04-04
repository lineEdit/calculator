package calculator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Assignment assignment = new Assignment();

        while (true) {
            InputLine input = new InputLine(scanner.nextLine());
            if (input.isCommand()) {
                Command command = new Command(input);
//                Analysis Warning else
                if (input.toString().compareToIgnoreCase("/exit") == 0) {
                    break;
                }
//                Analysis Warning else
                command.action();
            } else if (input.isAssignment()) {
                assignment.put(input);
            } else if (input.isExpression()) {
                Expression expression = new Expression(input, assignment);
                expression.calc();
            } else if (!input.isEmpty()) {
                System.out.println("Invalid expression");
            }
        }
    }
}
