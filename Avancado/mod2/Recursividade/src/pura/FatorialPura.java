package pura;

public class FatorialPura {

    //Complexidade assintótica O(n) visto que neste algoritmo a recursividade é somente para numeros menores, ou seja, um mesmo número não é chamado duas vezes.
    public long fatorial(int n) {

        if(n<=1) {
            return 1;
        }

        return n * fatorial(n-1);
    }
}
