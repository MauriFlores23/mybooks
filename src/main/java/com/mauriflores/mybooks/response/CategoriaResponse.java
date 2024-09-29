package com.mauriflores.mybooks.response;

import com.mauriflores.mybooks.model.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriaResponse {

    private List<Categoria> categorias;
}
