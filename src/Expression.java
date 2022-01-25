import java.util.ArrayList;
import java.util.List;

class ExpressionException extends Exception {
    public ExpressionException(String msg) {
        super(msg);
    }
}

class InvalidExpressionException extends ExpressionException {
    public InvalidExpressionException(String msg) {
        super(msg);
    }
}

class Expression {
    private static final int possibleOperatorsCount = 1;
    private static final int possibleOperandsCount = 2;
    private final List<String> operands = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();

    public Expression(String expression) throws ExpressionException {
        String[] values = expression.strip().split(" ");
        validate(values);
        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) {
                operands.add(values[i]);
            } else {
                operators.add(values[i]);
            }
        }
    }

    private static void validate(String[] operands) throws ExpressionException {
        if (operands.length != possibleOperandsCount + possibleOperatorsCount) {
            throw new InvalidExpressionException(
                    String.format(
                            "Expression must consist of %s operands and %s operators",
                            possibleOperandsCount,
                            possibleOperatorsCount
                    )
            );
        }
    }

    public List<String> getOperands() {
        return operands;
    }

    public List<String> getOperators() {
        return operators;
    }
}

