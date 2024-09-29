package com.mauriflores.mybooks.controller;

import com.mauriflores.mybooks.model.Categoria;
import com.mauriflores.mybooks.response.CategoriaResponseRest;
import com.mauriflores.mybooks.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService iCategoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultaCategorias(){
        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.buscarCategoria();
        return response;
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.buscarPorId(id);
        return response;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request){
        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.crear(request);
        return response;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria request, @PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.actualizar(request,id);
        return response;
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id){
        ResponseEntity<CategoriaResponseRest> response = iCategoriaService.eliminar(id);
        return response;
    }
}
