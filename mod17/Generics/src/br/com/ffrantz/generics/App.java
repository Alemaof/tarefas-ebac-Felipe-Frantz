package br.com.ffrantz.generics;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Carros> carros = new ArrayList<>();

        Carros hyundai = new Hyundai("Hyundai", "HB20",2015,"Branco");
        Carros chevrolet = new Chevrolet("Chevrolet", "Corsa", 2010, "Preto");
        carros.add(hyundai);
        carros.add(chevrolet);

        imprimirCarros(carros);

    }

    private static void imprimirCarros(List<? extends Carros> carros) {
        for (Carros carro : carros) {
            System.out.println(carro);
        }
    }
}
