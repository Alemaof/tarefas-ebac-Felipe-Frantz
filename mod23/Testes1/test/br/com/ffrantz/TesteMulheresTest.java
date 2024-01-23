package br.com.ffrantz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TesteMulheresTest {

    @Test
    public void testarListaMulheres() {
        String nomes = "Joao-M,Maria-F,Jose-M,Claudia-F";

        List<String> listaTeste = new ArrayList<>();
        listaTeste.add("Maria");
        listaTeste.add("Claudia");
        listaTeste.add("Emilia");


        Assertions.assertEquals(listaTeste,App.gerarLista("Joao-M,Maria-F,Ze-M,Claudia-F,Tulio-M,Roberto-M,Emilia-F"));
    }
}