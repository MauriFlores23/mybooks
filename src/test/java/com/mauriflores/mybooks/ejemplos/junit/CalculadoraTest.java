package com.mauriflores.mybooks.ejemplos.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    Calculadora cal;
    @BeforeAll
    public static void primero(){
        System.out.println("Primero!");
    }

    @AfterAll
    public static void ultimo(){
        System.out.println("Ultimo");
    }

    // Se ejecuta antes de cada prueba unitaria
    @BeforeEach
    public void instanciaObjeto(){
        cal = new Calculadora();
        System.out.println("Before Each!");
    }

    @AfterEach
    public void despuesTest(){
        System.out.println("After Each!");
    }


    @Test
    @DisplayName("Test calculadoraAssertEqualTest")
    public void calculadoraAssertEqualTest(){

        assertEquals(2,cal.sumar(1,1));
        assertEquals(3,cal.restar(4,1));
        assertEquals(20,cal.multiplicar(4,5));
        assertEquals(2,cal.dividir(6,3));
        System.out.println("*TestAssert*");
    }

    @Test
    @Disabled("No se ejecuta esta prueba")
    public void calculadoraTrueFalse(){
        assertTrue(cal.sumar(2,3)==5);
        assertTrue(cal.restar(5,3)==2);
        assertFalse(cal.multiplicar(5,4)==16);
        assertFalse(cal.dividir(5,3)==0);
        System.out.println("*AssertTrueFalse*");
    }
}
