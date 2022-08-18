public class Converters {
    private static int switchToDigit(char charAt) {
        return switch (charAt) {
            case 'M' -> 1000;
            case 'D' -> 500;
            case 'C' -> 100;
            case 'L' -> 50;
            case 'X' -> 10;
            case 'V' -> 5;
            case 'I' -> 1;
            default -> throw new NumberFormatException("Неправильно введена " +
                    "римская цифра.");
        };
    }

    public static int switchToArabicDigits(String romanNumber) {
        int result = 0;

        for (int i = 0; i < romanNumber.length() - 1; i++) {
            if (switchToDigit(romanNumber.charAt(i)) < switchToDigit(romanNumber.charAt(i + 1))) {
                result -= switchToDigit(romanNumber.charAt(i));
            } else {
                result += switchToDigit(romanNumber.charAt(i));
            }
        }
        result += switchToDigit(romanNumber.charAt(romanNumber.length() - 1));

        return result;
    }

    public static String switchToRomeDigits(int arabicResult) {
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
