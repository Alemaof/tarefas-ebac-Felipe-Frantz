package listaencadeada;

public class No<T> {

    private T no;
    private No<T> proximo;

    public T getNo() {
        return no;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    public No(T no) {
        this.no = no;
        this.proximo = null;
    }

    public No(T no, No<T> proximo) {
        this.no = no;
        this.proximo = proximo;
    }
}
