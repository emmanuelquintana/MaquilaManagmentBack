package com.NexaSoft.maquila.util;

import lombok.Getter;

public enum Estatus {

    ACTIVO(1L , "Activo"),
    INACTIVO(2L, "Inactivo");

    private Long id;

    private String descripcion;

    Estatus(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
