package com.mauriflores.mybooks.controllers;

import com.mauriflores.mybooks.controller.CategoriaRestController;
import com.mauriflores.mybooks.model.Categoria;
import com.mauriflores.mybooks.response.CategoriaResponseRest;
import com.mauriflores.mybooks.service.ICategoriaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class CategoriaRestControllerTest {

    @Mock
    private ICategoriaService service;

    @InjectMocks
    CategoriaRestController categoriaController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria = new Categoria(Long.valueOf(5),"Batman","Serie de Batman");
        when(service.crear(any(Categoria.class))).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));
        ResponseEntity<CategoriaResponseRest> response = categoriaController.crear(categoria);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
