package com.mauriflores.mybooks.model.dao;

import com.mauriflores.mybooks.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro,Long> {


}
