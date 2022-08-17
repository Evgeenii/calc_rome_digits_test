import java.io.IOException;

public class Calculator implements Operation {
    static final String[] ROME_DIGITS = {"I", "V", "X", "L", "C", "D", "M"};
    static final String[] ARABIC_DIGITS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    Utils utils = new Utils();

    void calc() throws IOException {
        String input = utils.inputFromConsole();
        String result = doOperation(input);
        utils.outputInConsole(input, result);
    }

    @Override
    public String doOperation(String input) {
        String operation = utils.getOperationFromInput(input);
        int[] numbers = utils.parseNumbersFromInput(input);
        int result = 0;

        result = switch (operation) {
            case "+" -> plus(numbers[0], numbers[1]);
            case "-" -> minus(numbers[0], numbers[1]);
            case "*" -> multiply(numbers[0], numbers[1]);
            case "/" -> divide(numbers[0], numbers[1]);

            default -> throw new IllegalArgumentException("Неправильный ввод. " +
                    "Введите два числа " +
                    "и операнд между ними " +
                    "пример: \"2 + 1\"");
        };

        return String.valueOf(result);
    }

    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }
}
