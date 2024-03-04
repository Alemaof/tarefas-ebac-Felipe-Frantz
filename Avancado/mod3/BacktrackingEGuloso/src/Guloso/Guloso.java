package Guloso;

import java.util.Arrays;

public class Guloso {

    public static void main(String[] args) {
        int troco = 24;

        int[] moedas = new int[] {5, 3, 2, 1};

        calcularMoedas(moedas, troco);
    }

    private static void calcularMoedas(int[] moedas, int troco) {

        int calcTroco = troco;
        int qtt = 0;

        int[] qttMoeda = new int[moedas.length];
        Arrays.sort(moedas);

        for(int i = moedas.length - 1; i > -1; i--) {
            while (calcTroco >= moedas[i]) {
                calcTroco -= moedas[i];
                qttMoeda[i]++;
                qtt++;
            }
        }
        System.out.println("Para o troce de " + troco + " foram utilizadas " + qtt + " moedas. Sendo:");
        for(int i = moedas.length - 1; i > -1; i--) {
            System.out.println(qttMoeda[i] + " moedas de " + moedas[i] + ".");
        }
    }
}
