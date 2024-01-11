import java.util.Scanner;

public class Notas {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Digite a primeira nota:");
        double nota1 = s.nextDouble();
        System.out.println("Digite a segunda nota:");
        double nota2 = s.nextDouble();
        System.out.println("Digite a terceira nota:");
        double nota3 = s.nextDouble();
        System.out.println("Digite a quarta nota:");
        double nota4 = s.nextDouble();

        calcularMedia(nota1, nota2, nota3, nota4);
    }

    private static void calcularMedia(double nota1, double nota2, double nota3, double nota4) {
        double media = (nota1 + nota2 + nota3 + nota4)/4;
        System.out.println("Sua media e " + media);
        if (media >= 7) {
            System.out.println("Voce foi aprovado");
        }
        else if (media >=5) {
            System.out.println("Voce esta de recuperacao");
        }
        else {
            System.out.println("Voce foi reprovado");
        }
    }
}
