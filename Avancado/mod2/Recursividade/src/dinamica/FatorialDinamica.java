package dinamica;

public class FatorialDinamica {

    int MAX_VALUE = 66;
    long[] fatorial = new long[MAX_VALUE];

    //Complexidade assintótica O(n)
    public long calcFatorial(int n) {
        for (int i = 0; i < MAX_VALUE; i++) {
            fatorial[i] = -1;
        }

        return fatorial(n);
    }

    //Complexidade assintótica O(n) visto que neste algoritmo a recursividade é somente para numeros menores, ou seja, um mesmo número não é chamado duas vezes.
    public long fatorial(int n) {
        if(fatorial[n] == -1) {
            if (n<=1) {
                fatorial[n] = 1;
            } else {
                fatorial[n] = n * fatorial(n - 1);
            }
        }
        return fatorial[n];
    }
}
