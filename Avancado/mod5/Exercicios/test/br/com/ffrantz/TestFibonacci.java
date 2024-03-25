package br.com.ffrantz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFibonacci {

    @Test
    public void testPrimeiroElemento_HappyPath() {
        assertEquals(0, Fibonacci.findElementDP(0));
    }

    @Test
    public void testSegundoElemento() {
        assertEquals(1, Fibonacci.findElementDP(1));
    }

    @Test
    public void testElementoN_HappyPath() {
        assertEquals(8, Fibonacci.findElementDP(6));
    }
}
