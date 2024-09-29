package com.mauriflores.mybooks.ejemplos.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class AssertNotEquals {

    @Test
    public void miTest(){
        assertNotEquals(1,2);
//        assertNotEquals(2,2);
    }
}
