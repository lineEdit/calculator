package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputLine {
    private String content;
    private Pattern pattern;
    private Matcher matcher;

    public InputLine(String content) {
        while (content.contains("--")) {
            content = content.replaceAll("--" , "+");
        }
        while (content.contains("++")) {
            content = content.replaceAll("\\+\\+" , "+");
        }
        content = content.replaceAll("\\+", " + ");
        content = content.replaceAll("-", " - ");
        content = content.replaceAll("\\(", " ( ");
        content = content.replaceAll("\\)", " ) ");
        content = content.replaceAll("\\*", " * ");
//        content = content.replaceAll("/", " / ");
        content = content.replaceAll("\\s{2}", " ");
        this.content = content.trim();
    }

    public boolean isInvalidAssignment() {
        int count = 0;
        for (char item : content.toCharArray()) {
            if (item == '=') {
                ++count;
            }
            if (count > 1) {
                return true;
            }
        }
        pattern = Pattern.compile("=\\s*([a-zA-Z]+\\d+)+\\s*");
        matcher = pattern.matcher(content);
        return matcher.find();
    }

    public boolean isInvalidIdentifier() {
        pattern = Pattern.compile("\\s*[a-zA-Z]+\\d+\\s*=");
        matcher = pattern.matcher(content);
        return matcher.find();
    }

    public boolean isAssignment() {
        pattern = Pattern.compile("\\s*[a-zA-Z]+\\s*=\\s*[a-zA-Z]*\\D*\\d*");
        matcher = pattern.matcher(content);
        return matcher.find();
    }

    public boolean isCommand() {
        pattern = Pattern.compile("/[a-z]+");
        matcher = pattern.matcher(content);
        return matcher.find();
    }

    public boolean isExpression() {
        pattern = Pattern.compile("[-+]?\\(?(\\w+\\s*[-+]*[*]?)+\\)?");
//        pattern = Pattern.compile("[-+]?\\w+(\\s*[-+]?\\s*[-+]?\\w+)*");
        matcher = pattern.matcher(content);
        return matcher.find();
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public String getContent() {
        return content;
    }
}