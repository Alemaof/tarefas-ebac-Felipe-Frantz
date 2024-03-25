package br.com.ffrantz;

/**
 * Calcula o n-ésimo elemento da sequência de Fibonacci.
 */
public class Fibonacci {

    private static final int MAX_ELEMENTS = 100;
    private static final int[] fibElements = new int[MAX_ELEMENTS];

    /**
     * Inicializa o array com a sequência de fibonacci para armazenar os valores já calculados e diminuir a complexidade dos cálculos.
     *
     * @param elementNumber o n-ésimo elemento da sequência de Fibonacci.
     * @return a chamada para o método do cálculo da sequência.
     */
    public static int findElementDP(int elementNumber) {
        for (int i = 0; i < MAX_ELEMENTS; i++) {
            fibElements[i] = -1;
        }

        return findElement(elementNumber);
    }

    /**
     * Faz a chamada recursiva para o cálculo da sequência de Fibonacci somente se o elemento ainda não tiver sido salvo no array.
     *
     * @param elementNumber o n-ésimo elemento da sequência de Fibonacci.
     * @return o valor do n-ésimo elemento da sequência de Fibonacci.
     */
    private static int findElement(int elementNumber) {
        if (fibElements[elementNumber] == -1) {
            if (elementNumber <= 1) {
                fibElements[elementNumber] = elementNumber;
            } else {
                fibElements[elementNumber] = findElement(elementNumber - 1) + findElement(elementNumber - 2);
            }
        }
        return fibElements[elementNumber];
    }
}
