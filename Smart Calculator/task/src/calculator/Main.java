package calculator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        final Scanner scanner = new Scanner("4 + 6 - 8\n\n\n2 - 3 - 4\n\n8 + 7 - 4\n1 +++ 2 * 3 -- 4\n/exit");
        final Scanner scanner = new Scanner(System.in);
        Assignment assignment = new Assignment();

        while (true) {
            InputLine input = new InputLine(scanner.nextLine());
            if (input.isCommand()) {
                Command command = new Command(input);
//                Analysis Warning
                if (input.toString().compareToIgnoreCase("/exit") == 0) {
                    break;
                }
//                Analysis Warning
                command.action();
            } else if (input.isInvalidIdentifier()) {
                System.out.println("Invalid identifier");
            } else if (input.isInvalidAssignment()) {
                System.out.println("Invalid assignment");
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
