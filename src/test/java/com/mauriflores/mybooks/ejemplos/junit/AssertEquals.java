package com.mauriflores.mybooks.ejemplos.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AssertEquals {

    @Test
    public void miTest(){
        assertEquals(1,1);
//        assertEquals(1,2);
    }
}
