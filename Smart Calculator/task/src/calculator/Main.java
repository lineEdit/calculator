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
            } else if (input.isAssignment()) {
                System.out.println("isAssignment");
            } else if (!input.isEmpty()) {
                System.out.println("Invalid expression");
            }
        }
    }
}
