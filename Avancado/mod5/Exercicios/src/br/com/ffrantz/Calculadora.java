package br.com.ffrantz;

/**
 * Classe que executa as quatro operações matemáticas básicas.
 */
public class Calculadora {

    public static int soma (int a, int b){
        return a+b;
    }

    public static int subtracao (int a, int b){
        return a-b;
    }

    public static int multiplicacao (int a, int b){
        return a*b;
    }

    /**
     * Não há divisão por 0. Caso o segundo valor seja 0, o método retorna uma exceção.
     *
     * @param a dividendo.
     * @param b divisor
     * @return o resultado da divisão.
     * @throws ArithmeticException caso o número
     */
    public static int divisao (int a, int b) throws ArithmeticException {
        int div;
        try {
            div = a/b;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Um número não pode ser dividido por 0.");
        }
        return div;
    }
}
