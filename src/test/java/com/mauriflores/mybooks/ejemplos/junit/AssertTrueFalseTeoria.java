package com.mauriflores.mybooks.ejemplos.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AssertTrueFalseTeoria {

    @Test
    public void testTrueFalse(){
        assertTrue(true);
        assertFalse(false);
    }

    @Test
    public void testBool(){
        boolean expression = 4 == 4;
        boolean expression2 = 3 == 4;
        assertTrue(expression);
        assertFalse(expression2);
    }
}
