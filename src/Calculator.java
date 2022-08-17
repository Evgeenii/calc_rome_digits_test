
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {

    static final String[] ROME_DIGITS = {"I", "V", "X", "L", "C", "D", "M"};
    static final String[] ARABIC_DIGITS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};


    public static void calc() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = doOperation(input);

        if (Utils.checkIfRomeDigits(input)) {
            String romeResult = Utils.swithToRome(Integer.parseInt(result));
            System.out.println(romeResult);

        } else if (Utils.checkIfArabicDigits(input)) {
            System.out.println(result);
        }
    }

    public static String doOperation(String input) {
        String operation = checkOperationFromInput(input);
        int[] numbers = parseIntFromInput(input);
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

    public static String checkOperationFromInput(String inputString) {
        String operation = "";
        long counter = 0;

        if (inputString.contains("+")) {
            operation = "+";
            counter = inputString.codePoints().filter(ch -> ch == '+').count();
        } else if (inputString.contains("-")) {
            operation = "-";
            counter = inputString.codePoints().filter(ch -> ch == '-').count();
        } else if (inputString.contains("*")) {
            operation = "*";
            counter = inputString.codePoints().filter(ch -> ch == '*').count();
        } else if (inputString.contains("/")) {
            operation = "/";
            counter = inputString.codePoints().filter(ch -> ch == '/').count();
        }

        if (counter > 1) {
            throw new IllegalStateException("Неверный ввод, формат математической операции " +
                    "не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        return operation;
    }


    public static int[] parseIntFromInput(String inputString) {
        boolean isRomeDigit = Utils.checkIfRomeDigits(inputString);
        String[] input = {inputString};
        String digitString = Arrays.stream(input)
                .map(string -> string.replaceAll(" ", ""))
                .flatMap(string -> Stream.of(string.split("[+\\-*/]")))
                .collect(Collectors.joining(","));
        String[] digits = digitString.split(",");
        int[] operands = new int[2];

        try {
            if (isRomeDigit) {
                operands[0] = Utils.switchToArabic(digits[0]);
                operands[1] = Utils.switchToArabic(digits[1]);
            } else if (!isRomeDigit) {
                operands[0] += Integer.parseInt(digits[0]);
                operands[1] += Integer.parseInt(digits[1]);
            } else {
                throw new NumberFormatException("Неправильный ввод, " +
                        "строка не является математической операцией ");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Кажется, вы не ввели операцию " +
                    "и слишком рано нажали Enter");
        }
        return operands;
    }


    public static int plus(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }

}
