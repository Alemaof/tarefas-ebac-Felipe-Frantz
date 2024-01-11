import java.util.Scanner;

public class Wrapper {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Digite um numero inteiro: ");
        int num = s.nextInt();
        Integer num1 = Integer.valueOf(num);

        System.out.println("Voce digitou o numero " + num + " e ele foi convertido no seu tipo wrapper " + num1);
    }
}
