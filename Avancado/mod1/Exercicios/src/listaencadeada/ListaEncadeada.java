package listaencadeada;

public class ListaEncadeada<T> {

    private No<T> inicio;
    private No<T> ultimo;
    private Integer tamanho = 0;

    //O método push faz a atribuição de valor à variáveis sem uso de loop. Complexidade O(1).
    public void push(T elemento) {
        No<T> no = new No<T>(elemento);
        if (tamanho == 0) {
            inicio = no;
        }
        else {
            ultimo.setProximo(no);
        }
        ultimo = no;
        tamanho++;
    }

    //O método pop utiliza o método buscaNo, que por sua vez usa um loop while para recuperar um valor. Complexidade O(n).
    public void pop() {
        if (tamanho>1) {
            No<T> atual = buscaNo(tamanho - 2);
            atual.setProximo(null);
            ultimo = atual;
            tamanho--;
        } else {
            limpaLista();
        }
    }

    private void limpaLista() {
        inicio = null;
        ultimo = null;
    }

    //O método insert utiliza o método buscaNo, que por sua vez usa um loop while para recuperar um valor. Complexidade O(n).
    public void insert(T elemento, int index) {
        if(tamanho == 0)
            push(elemento);
        else {
            No<T> novo = new No<T>(elemento);
            novo.setProximo(buscaNo(index));
            if(index==0) {
                inicio = novo;
            }
            else {
                buscaNo(index - 1).setProximo(novo);
            }
            tamanho++;
        }
    }

    //O método remove pode utilizaz o método buscaNo, que por sua vez usa um loop while para recuperar um valor.
    // O método também tem condições que dispensam o loop. Complexidade O(1) a O(n).
    public void remove(int index){
        if(tamanho==0)
            throw new NullPointerException("A lista está vazia");
        if(tamanho==1) {
            limpaLista();
            tamanho--;
        } else if(index==tamanho-1) {
            pop();
        } else if(index == 0) {
            inicio=buscaNo(index+1);
            tamanho--;
        } else {
            buscaNo(index - 1).setProximo(buscaNo(index + 1));
            tamanho--;
        }
    }

    private No<T> buscaNo(int index) {
        if(!(index >= 0 || index < tamanho)) {
            throw new NullPointerException("Índice não encontrado na lista");
        }
        int cont = 0;
        No<T> atual = inicio;
        while (cont < index) {
            atual = atual.getProximo();
            cont++;
        }
        return atual;
    }

    //O método elementAt utiliza o método buscaNo, que por sua vez usa um loop while para recuperar um valor. Complexidade O(n).
    public T elementAt(int index) {
        return buscaNo(index).getNo();
    }

    public Integer size() {
        return tamanho;
    }

    public String printlist() {
        StringBuilder sb = new StringBuilder().append("[");
        if (inicio == null){
            throw new NullPointerException("Lista está vazia");
        };
        No<T> atual = inicio;
        for(int i = 0; i < tamanho-1; i++) {
            sb.append(atual.getNo()).append(",");
            atual = atual.getProximo();
        }
        sb.append(atual.getNo()).append("]");
        return sb.toString();
    }

}
