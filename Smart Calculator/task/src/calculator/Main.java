package calculator;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            if (string.contains("/exit")) {
                System.out.println("Bye!");
                break;
            }
            if (string.isEmpty()) {
                continue;
            }
            String[] stringSplit = string.split("\\s+");
            int[] ints = new int[stringSplit.length];
            for (int i = 0; i < stringSplit.length; ++i) {
                ints[i] = Integer.parseInt(stringSplit[i]);
            }
            switch (ints.length) {
                case 1:
                    System.out.println(ints[0]);
                    break;
                case 2:
                    System.out.println(ints[0] + ints[1]);
                    break;
            }
        }
    }
}
