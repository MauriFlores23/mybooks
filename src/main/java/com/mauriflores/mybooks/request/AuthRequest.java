package com.mauriflores.mybooks.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private String contrasena;
}
