import java.util.List;
import java.util.Arrays;

class CalcException extends Exception {
    public CalcException(String msg) {
        super(msg);
    }
}

class InvalidArgumentException extends CalcException {
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}

public class Calc {
    private static final List<String> possibleOperators = Arrays.asList("+", "-", "/", "*");

    static int calculate(int firstOp, String operator, int secondOp) throws CalcException {

        switch (operator) {
            case "+" -> {
                return firstOp + secondOp;
            }
            case "-" -> {
                return firstOp - secondOp;
            }
            case "/" -> {
                return firstOp / secondOp;
            }
            case "*" -> {
                return firstOp * secondOp;
            }
            default -> {
                throw new InvalidArgumentException(
                        "Incorrect operator. Correct is: " + String.join(", ", possibleOperators)
                );
            }
        }
    }

}
