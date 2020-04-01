package calculator;

public class InputLine {
    private String content;

    public InputLine(String content) {
        this.content = content.trim().toLowerCase();
    }

    public boolean isCommand() {
        return content.matches("/[a-z]+");
    }

    public boolean isExpression() {
        return content.matches("[-+]?\\d+(\\s[-+]\\s[-+]?\\d+)*");
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public String getContent() {
        return content;
    }
}