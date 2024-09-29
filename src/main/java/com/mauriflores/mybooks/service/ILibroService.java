package com.mauriflores.mybooks.service;

import com.mauriflores.mybooks.model.Libro;
import com.mauriflores.mybooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;


public interface ILibroService {

    public ResponseEntity<LibroResponseRest> buscarLibros();

    public ResponseEntity<LibroResponseRest> buscarPorId(Long id);

    public ResponseEntity<LibroResponseRest> crear(Libro libro);

    public ResponseEntity<LibroResponseRest> actualizar(Libro libro,Long id);

    public ResponseEntity<LibroResponseRest> eliminar(Long id);
}
