package fila;

public class Fila<T> {

    private Integer posicaoFila;

    private T[] fila;

    public Fila(Integer n) {
        this.posicaoFila = -1;
        fila = (T[]) new java.lang.Object[n];
    }

    //O método enqueue faz atribuições de valores sem uso de loop. Complexidade O(1).
    public void enqueue(T value) {
        if(this.posicaoFila < this.fila.length - 1) {
            posicaoFila++;
            fila[posicaoFila] = value;
        }
    }

    //O método dequeue faz a realocação de todos os valores da fila utilizando um loop for. Complexidade O(n).
    public T dequeue() {
        if(isEmpty())
            return null;
        T result = fila[0];
        for (Integer i = 0; i < posicaoFila; i++){
            fila[i] = fila[i+1];
        }
        fila[posicaoFila] = null;
        posicaoFila--;
        return result;
    }

    //O método rear somente retorna uma variável, sem uso de loop. Complexidade O(1).
    public T rear() {
        if (isEmpty())
            return null;
        return fila[posicaoFila];
    }

    //O método front somente retorna uma variável, sem uso de loop. Complexidade O(1).
    public T front() {
        return fila[0];
    }

    public Integer size() {
        return posicaoFila+1;
    }

    public Boolean isEmpty() {
        if (this.posicaoFila == -1)
            return true;
        return false;
    }
}
