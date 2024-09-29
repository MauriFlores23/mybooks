package com.mauriflores.mybooks.response;

import com.mauriflores.mybooks.model.Libro;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibroResponse {

    private List<Libro> libros;
}
