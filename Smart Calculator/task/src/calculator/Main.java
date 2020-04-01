package calculator;

import java.util.ArrayList;
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
            if (string.contains("/help")) {
                System.out.println("The program calculates the sum of numbers");
            }
            if (string.isEmpty()) {
                continue;
            }
            List<Integer> integerList = new ArrayList<>();
            List<String> stringList = new ArrayList<>();
            String[] stringSplit = string.split("\\s+");
            for (String item : stringSplit) {
                try {
                    integerList.add(Integer.parseInt(item));
                } catch (Exception e) {
                    while (item.contains("--")) {
                        item = item.replaceAll("--" , "+");
                    }
                    while (item.contains("++")) {
                        item = item.replaceAll("\\+\\+" , "+");
                    }
                    stringList.add(item);
                }
            }
            System.out.println(stringList);
            System.out.println(integerList);
            Integer sum = 0;
            for (Integer integer : integerList) {
                sum += integer
            }
        }
    }
}
