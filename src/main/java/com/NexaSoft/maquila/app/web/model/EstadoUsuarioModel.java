package com.NexaSoft.maquila.app.web.model;

import com.NexaSoft.maquila.app.domain.entity.EstadoUsuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
public class EstadoUsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ApiModelProperty(value = "ID del estado del usuario", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty(value = "Nombre del estado del usuario", example = "Activo")
    private String descripcion;

    public static EstadoUsuario transformaEntidad(EstadoUsuarioModel modelo) {
        return new EstadoUsuario(modelo.getId(), modelo.getDescripcion());
    
}
}