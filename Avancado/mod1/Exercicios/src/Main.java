import fila.Fila;
import listaencadeada.ListaEncadeada;
import pilha.Pilha;

public class Main {
    public static void main(String[] args) {

        exemploPilha();
        exemploFila();
        exemploListaEncadeada();
    }

    private static void exemploPilha() {

        System.out.println("*****Exemplo de Pilha: *****");

        System.out.println("Criando uma pilha...");
        Pilha<String> pilha = new Pilha<>(5);

        System.out.println("Verificando o tamanho da pilha vazia: " + pilha.size());
        System.out.println("Certificando que a pilha está vazia: " + pilha.isEmpty());

        System.out.println("Adicionando um Primeiro valor na pilha...");
        pilha.push("Primeiro Valor");

        System.out.println("Certificando que a pilha não está vazia: " + pilha.isEmpty());
        System.out.println("Tamanho da pilha: " + pilha.size());
        System.out.println("O valor no topo da pilha: " + pilha.top());

        System.out.println("Removendo o valor no topo da pilha: " + pilha.pop());
        System.out.println("Verificando o tamanho da pilha vazia: " + pilha.size());
        System.out.println("Certificando que a pilha está vazia: " + pilha.isEmpty());

        System.out.println("Adicionando 4 valores na pilha...");
        pilha.push("Primeiro Valor");
        pilha.push("Segundo Valor");
        pilha.push("Terceiro Valor");
        pilha.push("Quarto Valor");

        System.out.println("Tamanho da pilha: " + pilha.size());
        System.out.println("O valor no topo da pilha: " + pilha.top());
        System.out.println("Adicionando mais dois valores à pilha, mas na pilha só cabem 5...");

        pilha.push("Quinto Valor");
        pilha.push("Sexto Valor");

        System.out.println("Tamanho da pilha: " + pilha.size());
        System.out.println("O valor no topo da pilha: " + pilha.top());
        System.out.println("******************************");
        System.out.println("");
    }

    public static void exemploFila() {

        System.out.println("*****Exemplo de Fila: *****");

        System.out.println("Criando uma fila vazia...");
        Fila<String> fila = new Fila<>(5);

        System.out.println("Verificando o tamanho da fila: " + fila.size());
        System.out.println("Certificando que a fila está vazia: " + fila.isEmpty());

        System.out.println("Adicionando um Primeiro valor na fila...");
        fila.enqueue("Primeiro Valor");

        System.out.println("Certificando que a fila não está vazia: " + fila.isEmpty());
        System.out.println("Tamanho da Fila: " + fila.size());
        System.out.println("O Primeiro valor da fila: " + fila.front());
        System.out.println("O Último valor da fila: " + fila.rear());

        System.out.println("Removendo o Primeiro valor da fila..." + fila.dequeue());
        System.out.println("O Primeiro valor da fila: " + fila.front());
        System.out.println("O Último valor da fila: " + fila.rear());
        System.out.println("Verificando o tamanho da fila: " + fila.size());
        System.out.println("Certificando que a fila está vazia: " + fila.isEmpty());

        System.out.println("Adicionando 4 valores na fila...");
        fila.enqueue("Primeiro Valor");
        fila.enqueue("Segundo Valor");
        fila.enqueue("Terceiro Valor");
        fila.enqueue("Quarto Valor");

        System.out.println("Tamanho da Fila: " + fila.size());
        System.out.println("O Primeiro valor da fila: " + fila.front());
        System.out.println("O Último valor da fila: " + fila.rear());
        System.out.println("Adicionando mais dois valores à fila, mas na fila só cabem 5...");
        fila.enqueue("Quinto Valor");
        fila.enqueue("Sexto Valor");
        System.out.println("Tamanho da Fila: " + fila.size());
        System.out.println("O Primeiro valor da fila: " + fila.front());
        System.out.println("O Último valor da fila: " + fila.rear());

        System.out.println("Removendo o primeiro valor da fila: " + fila.dequeue());
        System.out.println("O Primeiro valor da fila: " + fila.front());
        System.out.println("O Último valor da fila: " + fila.rear());
        System.out.println("Tamanho da Fila: " + fila.size());
        System.out.println("******************************");
        System.out.println("");
    }

    private static void exemploListaEncadeada() {
        System.out.println("*****Exemplo de Lista Encadeada: *****");

        System.out.println("Criando uma lista...");
        ListaEncadeada<String> lista = new ListaEncadeada<>();

        System.out.println("Adicionando primeiro nó à lista...");
        lista.insert("Primeiro Nó", 0);
        System.out.println("Tamanho da lista: " + lista.size());

        System.out.println("A lista: " + lista.printlist());

        System.out.println("Removendo nó no index 0...");
        lista.remove(0);

        System.out.println("Tamanho da lista: " + lista.size());

        System.out.println("Adicionando três nós à lista...");
        lista.push("Primeiro Nó");
        lista.push("Segundo Nó");
        lista.push("Terceiro Nó");

        System.out.println("Tamanho da lista: " + lista.size());
        System.out.println("A lista: " + lista.printlist());

        System.out.println("Elemento no índice 2: " + lista.elementAt(2));

        System.out.println("Removendo o último nó...");
        lista.pop();

        System.out.println("O tamanho da lista: " + lista.size());
        System.out.println("A lista: " + lista.printlist());

        System.out.println("O último nó da lista: " + lista.elementAt(lista.size()-1));

        System.out.println("Inserindo nó no início...");
        lista.insert("Nó no inicio", 0);

        System.out.println("A lista: " + lista.printlist());

        System.out.println("Inserindo nó no meio...");
        lista.insert("Nó no meio", 1);

        System.out.println("A lista: " + lista.printlist());

        System.out.println("Removendo o último elemento com index...");
        lista.remove(lista.size()-1);

        System.out.println("A lista: " + lista.printlist());

        System.out.println("Removendo nó no index 1");
        lista.remove(1);

        System.out.println("A lista: " + lista.printlist());

        System.out.println("Removendo primeiro elemento da lista...");
        lista.remove(0);

        System.out.println("A lista: " + lista.printlist());

        System.out.println("******************************");
        System.out.println("");
    }
}