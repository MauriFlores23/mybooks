package com.mauriflores.mybooks.model.dao;

import com.mauriflores.mybooks.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria,Long> {
}
