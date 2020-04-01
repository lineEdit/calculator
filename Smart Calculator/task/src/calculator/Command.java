package calculator;

class Command {
    private String command;

    public Command(InputLine input) {
        command = input.getContent();
    }

    public void action() {
        switch (command) {
            case "/exit":
                exit();
                break;
            case "/help":
                help();
                break;
            default:
                unknown();
                break;
        }
    }

    private void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private void help() {
        System.out.println("The program calculates the sum of numbers.");
    }

    private void unknown() {
        System.out.println("Unknown command");
    }
}