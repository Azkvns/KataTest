import java.util.*;

class ValidatorException extends Exception {
    public ValidatorException(String message) {
        super(message);
    }
}

class EmptyArgumentException extends ValidatorException {
    public EmptyArgumentException(String message) {
        super(message);
    }
}

class InvalidValueException extends ValidatorException {
    public InvalidValueException(String message) {
        super(message);
    }
}

public class RomanNumberConverter {
    private static final List<Integer> arabicDigits = Arrays.asList(1, 5, 10, 50, 100);
    private static final List<String> romanDigits = Arrays.asList("I", "V", "X", "L", "C");

    static private int getArabicDigit(String romanDigit) {
        return arabicDigits.get(romanDigits.indexOf(romanDigit));
    }

    static private String getRomanDigit(int arabicDigit) {
        return romanDigits.get(arabicDigits.indexOf(arabicDigit));
    }

    public static int toArabic(String romanValue) throws ValidatorException {
        Validator.isRomanValid(romanValue);

        int result = 0;

        for (int i = 0; i < romanValue.length(); i++) {
            String currentRomanDigit = romanValue.charAt(i) + "";
            int currentValue = getArabicDigit(currentRomanDigit);

            String previousRomanDigit = romanValue.charAt(Math.max(i - 1, 0)) + "";
            int previousValue = getArabicDigit(previousRomanDigit);

            if (currentValue <= previousValue) {
                result += currentValue;
            }

            if (currentValue > previousValue) {
                result = currentValue - previousValue;
            }

        }
        return result;
    }

    public static String toRoman(int arabicValue) throws ValidatorException {
        Validator.isArabicValid(arabicValue);
        String[] digits = (arabicValue + "").split("");
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < digits.length; i++) {
            int currentArabicDigit = Integer.parseInt(digits[i]);
            int grade = (int)Math.pow(10, digits.length - i - 1);

            String baseRomanDigit;
            String modRomanDigit;

            switch (currentArabicDigit) {
                case 1, 2, 3 -> {
                    baseRomanDigit = getRomanDigit(grade);
                    result.add(baseRomanDigit.repeat(currentArabicDigit));
                }
                case 6, 7, 8 -> {
                    baseRomanDigit = getRomanDigit(5 * grade);
                    modRomanDigit = getRomanDigit(grade);
                    result.add(baseRomanDigit + modRomanDigit.repeat(currentArabicDigit - 5));
                }
                case 4, 9 -> {
                    int nearestGraterDigit = currentArabicDigit + 1;
                    int decrementGrade = nearestGraterDigit / 5;
                    baseRomanDigit = getRomanDigit(nearestGraterDigit * grade);
                    modRomanDigit = romanDigits.get(romanDigits.indexOf(baseRomanDigit) - decrementGrade);
                    result.add(modRomanDigit + "" + baseRomanDigit);
                }
                case 5 -> {
                    baseRomanDigit = getRomanDigit(currentArabicDigit * grade);
                    result.add(baseRomanDigit);
                }
            }
        }
        return String.join("", result);
    }

    private static class Validator {
        private static boolean isRomanValid(String value) throws ValidatorException {
            if (value.isEmpty()) {
                throw new EmptyArgumentException("Value must be not empty");
            }

            if (value.matches("\\s")) {
                throw new InvalidValueException("Value must not contain spaces");
            }

            if (value.matches("\\d")) {
                throw new InvalidValueException("Value must not contain arabic digits");
            }

            if (!value.matches("^[IVXM]*$")) {
                throw new InvalidValueException("Value must contain only roman digits");
            }

            return true;
        }

        private static boolean isArabicValid(int value) throws ValidatorException {
            if (value <= 0) {
                throw new InvalidValueException("Value must be greater than 0");
            }

            return true;
        }
    }


}
