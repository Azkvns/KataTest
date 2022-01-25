import java.util.Scanner;

public class First {
    public static void main(String[] args) throws ValidatorException, Exception {
        String name = "Vasa";
        Scanner sc = new Scanner(System.in);
//        System.out.println(109 - 109 / 60 * );
        while (true) {
            String value = sc.nextLine();
            String res = new Calculation(value).calculate();
            System.out.println(res);
        }

    }
}