package br.com.ffrantz.reflections;



public class App {
    public static void main(String[] args) {

        ClasseAnnotation ref = new ClasseAnnotation();
        Tabela tab = ref.getClass().getDeclaredAnnotation(Tabela.class);
        System.out.println("Nome da tabela: " + tab.value());
    }
}
