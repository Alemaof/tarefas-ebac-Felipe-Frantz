package pilha;

public class Pilha<T> {

    private int posicaoPilha;

    private T[] pilha;

    public Pilha(Integer n) {
        this.posicaoPilha = -1;
        pilha = (T[]) new java.lang.Object[n];
    }

    //A complexidade assintótica do método push é O(1), visto que há apenas a atribuição de valor às variáveis, sem uso de loop.
    public void push(T value) {
        if(this.posicaoPilha < this.pilha.length - 1) {
            posicaoPilha++;
            pilha[posicaoPilha] = value;
        }
    }

    //A complexidade assintótica do método push é O(1), porque também é uma reatribuição de valor a variáveis, sem loop.
    public T pop() {
        if(isEmpty())
            return null;
        T result = pilha[posicaoPilha];
        pilha[posicaoPilha] = null;
        posicaoPilha--;
        return result;
    }

    public T top() {
        if(isEmpty())
            return null;
        return pilha[posicaoPilha];
    }

    public Boolean isEmpty() {
        if(posicaoPilha==-1)
            return true;
        return false;
    }

    public Integer size() {
        return posicaoPilha+1;
    }
}
