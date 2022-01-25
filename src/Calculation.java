import java.util.*;

public class Calculation {
    Expression expression;
    Integer result;
    Boolean isArabic;

    public Calculation(String expression) throws ExpressionException {
        this.expression = new Expression(expression);
        operandsTypeCheck(this.expression.getOperands());
    }

    private void operandsTypeCheck(List<String> operands) throws InvalidExpressionException {
        Set<Boolean> set = new HashSet<Boolean>();
        for (String op : operands) {
            set.add(isInteger(op));
        }
        if (set.size() != 1) {
            throw new InvalidExpressionException("Operands must be of the same type");
        }

        isArabic = set.contains(true);
    }

    private void validateOperands(List<Integer> operands) throws InvalidExpressionException {
        for (Integer op : operands) {
            if (op < 1) {
                throw new InvalidExpressionException("Arguments must be greater than 0");
            }
            if (op > 10) {
                throw new InvalidExpressionException("Arguments must be lower than 11");
            }
        }
    }

    private static Boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private List<Integer> convertToInteger(List<String> values) throws ValidatorException {
        List<Integer> converted = new ArrayList<>(values.size());

        for (String value : values) {
            if (this.isArabic) {
                converted.add(Integer.parseInt(value));
            } else {
                converted.add(RomanNumberConverter.toArabic(value));
            }
        }

        return converted;
    }

    public String calculate() throws ExpressionException, ValidatorException, CalcException {
        Iterator<String> operatorsIterator = expression.getOperators().iterator();
        List<Integer> operands = convertToInteger(expression.getOperands());
        validateOperands(operands);
        Iterator<Integer> operandsIterator = operands.iterator();

        result = operandsIterator.next();

        while (operandsIterator.hasNext()) {
            result = Calc.calculate(result, operatorsIterator.next(), operandsIterator.next());
        }

        return isArabic ? result.toString() : RomanNumberConverter.toRoman(result);
    }

}
