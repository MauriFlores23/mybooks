package com.mauriflores.mybooks.service;


import com.mauriflores.mybooks.model.Categoria;
import com.mauriflores.mybooks.model.dao.ICategoriaDao;
import com.mauriflores.mybooks.response.CategoriaResponseRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaServiceImplTest {

    @InjectMocks
    CategoriaServiceImpl service;

    @Mock
    ICategoriaDao categoriaDao;

    List<Categoria> categoriaList = new ArrayList<>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarCategorias();
    }

    @Test
    public void buscarCategoriasTest(){

        when(categoriaDao.findAll()).thenReturn(categoriaList);
        ResponseEntity<CategoriaResponseRest> response = service.buscarCategoria();
        assertEquals(4,response.getBody().getCategoriaResponse().getCategorias().size());
        // Se invoca a categoriaDao una vez
        verify(categoriaDao,times(1)).findAll();

    }

    public void cargarCategorias(){
        Categoria catUno = new Categoria( Long.valueOf(1),"Accion","Libros de accion");
        Categoria catDos = new Categoria(Long.valueOf(2),"Abarrotes","Libros de abarrotes");
        Categoria catTres = new Categoria(Long.valueOf(3),"Bebidas","Libros de bebidas carbonatadas");
        Categoria catCuatro = new Categoria(Long.valueOf(4),"Carne","Libros de carne vacuna");
        categoriaList.add(catUno);
        categoriaList.add(catDos);
        categoriaList.add(catTres);
        categoriaList.add(catCuatro);
    }
}
