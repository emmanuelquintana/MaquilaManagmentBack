package com.NexaSoft.maquila.util;

public enum EstadoCliente {
    ACTIVO(1L, "Activo"),
    INACTIVO(2L, "Inactivo");

    private final Long id;
    private final String description;

    EstadoCliente(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
