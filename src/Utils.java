import java.util.Arrays;

public class Utils {

    public static boolean checkIfRomeDigits(String inputString) {
        return Arrays.stream(Calculator.ROME_DIGITS).anyMatch(inputString::contains);
    }

    public static boolean checkIfArabicDigits(String inputString) {
        return Arrays.stream(Calculator.ARABIC_DIGITS).anyMatch(inputString::contains);
    }

    public static int switchToArabic(String inputString) {
        int result;

        result = switch (inputString) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalStateException("Неправильный ввод, используются " +
                    "одновременно разные системы счисления");
        };

        return result;
    }

    public static String swithToRome(int arabicResult) {
        int thousands = arabicResult / 1000;
        int hundreds = (arabicResult - (thousands * 1000)) / 100;
        int tens = (arabicResult - (thousands * 1000)) % 100 / 10;
        int units = (arabicResult - (thousands * 1000)) % 10;
        String romeResult = "";
        for (int i = 0; i < thousands; i++) {
            romeResult += 'M';
        }

        if (hundreds < 4) {
            for (int i = 0; i < hundreds; i++) {
                romeResult += 'C';
            }
        } else if (hundreds == 4) {
            romeResult += "CD";

        } else if (hundreds >= 5 && hundreds < 9) {
            romeResult += 'D';
            for (int i = 0; i < hundreds - 5; i++) {
                romeResult += 'C';
            }
        } else if (hundreds == 9) {
            romeResult += "CM";
        }

        if (tens < 4) {
            for (int i = 0; i < tens; i++) {
                romeResult += 'X';
            }
        } else if (tens == 4) {
            romeResult += "XL";
        } else if (tens >= 5 && tens < 9) {
            romeResult += 'L';
            for (int i = 0; i < tens - 5; i++) {
                romeResult += 'X';
            }
        } else if (tens == 9) {
            romeResult += "XC";
        }

        if (units < 4) {
            for (int i = 0; i < units; i++) {
                romeResult += 'I';
            }
        } else if (units == 4) {
            romeResult += "IV";

        } else if (units >= 5 && units < 9) {
            romeResult += 'V';
            for (int i = 0; i < units - 5; i++) {
                romeResult += 'I';
            }
        } else if (units == 9) {
            romeResult += "IX";
        }

        return romeResult;
    }
}
