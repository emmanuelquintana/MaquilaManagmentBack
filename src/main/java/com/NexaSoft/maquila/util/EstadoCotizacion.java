package com.NexaSoft.maquila.util;

public enum EstadoCotizacion {
    PENDIENTE(1L, "Pendiente"),
    APROBADA(2L, "Aprobada"),
    RECHAZADA(3L, "Rechazada");

    private final Long id;
    private final String description;

    EstadoCotizacion(Long id, String description) {
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
