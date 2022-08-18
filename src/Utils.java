import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public boolean checkIfRomeDigits(String inputString) {
        return Arrays.stream(Calculator.ROME_DIGITS).anyMatch(inputString::contains);
    }

    public boolean checkIfArabicDigits(String inputString) {
        return Arrays.stream(Calculator.ARABIC_DIGITS).anyMatch(inputString::contains);
    }

    public String getOperationFromInput(String inputString) {
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

    public int[] parseNumbersFromInput(String inputString) {
        boolean isRomeDigit = checkIfRomeDigits(inputString);
        boolean isArabicDigit = checkIfArabicDigits(inputString);
        String[] input = {inputString};
        int[] operands = new int[2];

        String[] digits = Arrays.stream(input)
                .map(string -> string.replaceAll(" ", ""))
                .flatMap(string -> Stream.of(string.toUpperCase(Locale.ROOT).split("[+\\-*/]")))
                .collect(Collectors.joining(",")).split(",");

        try {
            if (isRomeDigit) {
                operands[0] = Converters.switchToArabicDigits(digits[0]);
                operands[1] = Converters.switchToArabicDigits(digits[1]);

            } else if (isArabicDigit) {
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

        if (operands[0] > 10 || operands[1] > 10) {
            throw new IllegalArgumentException("Неверный ввод, " +
                    "одно из чисел больше 10 ");
        }
        return operands;
    }

    public String inputFromConsole() throws IOException {
        String string;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            string = reader.readLine();
            reader.close();
        }
        if (string.isEmpty()) {
            throw new IllegalArgumentException("Вы ввели пустую строку");
        }
        return string;
    }

    public void outputInConsole(String input, String result) {
        if (checkIfRomeDigits(input)) {
            if (Integer.parseInt(result) < 0) {
                throw new IllegalArgumentException("Неправильный вывод. " +
                        "в римской системе нет отрицательных чисел");
            }
            String romeResult = Converters.switchToRomeDigits(Integer.parseInt(result));
            System.out.println(romeResult);
        } else if (checkIfArabicDigits(input)) {
            System.out.println(result);
        }
    }
}
