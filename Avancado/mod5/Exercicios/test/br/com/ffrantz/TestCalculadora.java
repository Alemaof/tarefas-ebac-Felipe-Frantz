package br.com.ffrantz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculadora {

    @Test
    public void testSoma_HappyPath() {

        assertEquals(5,Calculadora.soma(2,3));
    }

    @Test
    public void testSubtracao_HappyPath() {

        assertEquals(3,Calculadora.subtracao(7,4));
    }

    @Test
    public void testMultiplicacao_HappyPath() {

        assertEquals(6,Calculadora.multiplicacao(3,2));
    }

    @Test
    public void testDivisao_HappyPath() {

        assertEquals(3,Calculadora.divisao(9,3));
    }

    @Test (expected = ArithmeticException.class)
    public void testDivisao_DivisaoPorZero() {

        assertEquals(5,Calculadora.divisao(4,0));
    }
}
