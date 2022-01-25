import java.util.Scanner;

public class KataCalculator {
    public static void main(String[] args) throws ValidatorException, ExpressionException, CalcException {
        Scanner sc = new Scanner(System.in);
            String expression = sc.nextLine();
            String result = new Calculation(expression).calculate();
            System.out.println(result);
    }
}