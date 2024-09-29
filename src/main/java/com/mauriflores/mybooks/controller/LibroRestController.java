package com.mauriflores.mybooks.controller;

import com.mauriflores.mybooks.model.Libro;
import com.mauriflores.mybooks.response.LibroResponseRest;
import com.mauriflores.mybooks.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class LibroRestController {

    @Autowired
    private ILibroService iLibroService;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> consultaLibros(){
        ResponseEntity<LibroResponseRest> response = iLibroService.buscarLibros();
        return response;
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> consultaPorId(@PathVariable Long id){
        ResponseEntity<LibroResponseRest> response = iLibroService.buscarPorId(id);
        return response;
    }

    @PostMapping("/libros")
    public ResponseEntity<LibroResponseRest> crear(@RequestBody Libro request){
        ResponseEntity<LibroResponseRest> response = iLibroService.crear(request);
        return response;
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> actualizar(@RequestBody Libro request,@PathVariable Long id){
        ResponseEntity<LibroResponseRest> response = iLibroService.actualizar(request,id);
        return response;
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> eliminar(@PathVariable Long id){
        ResponseEntity<LibroResponseRest> response = iLibroService.eliminar(id);
        return response;
    }

}
