import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Parte2 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String lerNomes;
        String[] arrayNomes;
        List<String> homens = new ArrayList<>();
        List<String> mulheres = new ArrayList<>();

        System.out.println("Digite os nomes seguido do sexo das pessoas separados por virgulas e sem espaços:");
        System.out.println("Exemplo: Joao-M,Maria-F");
        lerNomes = s.next();
        arrayNomes = lerNomes.split(",");

        for (String nome : arrayNomes) {
            if (nome.charAt(nome.length()-1) == 'M'){
                nome = nome.replace("-M","");
                homens.add(nome);
            } else if (nome.charAt(nome.length()-1) == 'F') {
                nome = nome.replace("-F","");
                mulheres.add(nome);
            }
        }

        Collections.sort(homens);
        Collections.sort(mulheres);

        System.out.println("O conjunto de homens e composto por: " + homens);
        System.out.println("O conjunto de mulheres e composto por: " + mulheres);
    }
}
