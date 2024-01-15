import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Parte1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite os nomes que deseja armazenar separados por virgulas:");
        String nomes = s.next();

        List<String> listaNomes = new ArrayList<>();
        for (String nome : nomes.split(",")) {
            listaNomes.add(nome);
        }
        System.out.println("A lista de nomes digitados e: " + listaNomes);
        Collections.sort(listaNomes);
        System.out.println("A lista de nomes ordenada por ordem alfabetica e: " + listaNomes);
    }
}
