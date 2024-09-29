package com.mauriflores.mybooks.ejemplos.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertArrayEquals {

    @Test
    public void pruebaArregloIgual(){
        String[] arre1 = {"a","b"};
        String[] arre2 = {"a","b"};
        String[] arre3 = {"a","b","c"};
        String[] arre4 = {"a","e","c"};
        int[] arre5 = {1,2};
        String[] arre6 = {"1","2"};
        assertArrayEquals(arre1,arre2);
//        assertArrayEquals(arre1,arre3);
//        assertArrayEquals(arre3,arre4);
//        assertArrayEquals(arre5,arre6);
    }
}
