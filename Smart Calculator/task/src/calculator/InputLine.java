package calculator;

public class InputLine {
    private String content;

    public InputLine(String content) {
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
        return content.matches("^\\s*[a-zA-Z]+\\d+\\s*=\\s*[a-zA-Z]+\\d+\\s*");
    }

    public boolean isInvalidIdentifier() {
        return content.matches("\\s*[a-zA-Z]+\\d+\\s*");
    }

    public boolean isAssignment() {
        return content.matches("\\s*[a-zA-Z]+\\s*=\\s*[a-zA-Z]*\\D*\\d*");
    }

    public boolean isCommand() {
        return content.matches("/[a-z]+");
    }

    public boolean isExpression() {
        return content.matches("[-+]?\\w+(\\s*[-+]?\\s*[-+]?\\w+)*");
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public String getContent() {
        return content;
    }
}