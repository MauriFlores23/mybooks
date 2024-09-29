package com.mauriflores.mybooks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="libro")
@Getter
@Setter
public class Libro implements Serializable {

   private  static final long serialVersionUID = 6992684974740872661L;

   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Long id;
   private String nombre;
   private String descripcion;

   // Define como cargar info. LAZY cuando llame a Libro recien invoque a Categoria
   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
   private Categoria categoria;

}
