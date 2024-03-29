package br.com.ffrantz;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite os nomes seguido do sexo das pessoas separados por virgulas e sem espaços:");
        System.out.println("Exemplo: Joao-M,Maria-F");
        String nomes = s.next();

        List<String> listaMulheres = gerarLista(nomes);
        System.out.println("As mulheres informadas sao:");
        listaMulheres.forEach(System.out::println);
    }

    public static List<String> gerarLista(String nomes) {
        List<String> listaMulheres = Arrays.stream(nomes.split(",")).
                filter(pessoa -> pessoa.charAt(pessoa.length() - 1) == 'F')
                .map(pessoa -> pessoa.replace("-F",""))
                .collect(Collectors.toList());

        return listaMulheres;
    }
}
