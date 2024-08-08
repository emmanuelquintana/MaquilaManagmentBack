package com.NexaSoft.maquila.app.web.model;

import com.NexaSoft.maquila.app.domain.entity.EstadoUsuario;
import com.NexaSoft.maquila.app.domain.entity.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

/**
 * Modelo de datos para un usuario.
 */
@Getter
@Setter
@ApiModel(description = "Modelo de datos para un usuario")
public class UsuariosModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "UUID del usuario", example = "4a067e9a-0a0a-4a0a-8a0a-8a0a8a0a8a0a")
    private UUID uuid;

    @ApiModelProperty(value = "Nombre del usuario", required = true, example = "Juan Pérez")
    private String nombre;

    @ApiModelProperty(value = "Email del usuario", required = true, example = "juan.perez@example.com")
    private String email;

    @ApiModelProperty(value = "ID del estado del usuario", required = true, example = "1")
    private EstadoUsuarioModel estatus;

    @ApiModelProperty(value = "Hash de la contraseña del usuario", required = true, example = "$2a$10$EixZaYVK1fsbw1ZfbX3OXe")
    private String passwordHash;

    @ApiModelProperty(value = "Fecha de creación del usuario", example = "2021-08-01T12:34:56.789Z")
    private Timestamp creadoEn;

   public static final Function<Usuario, UsuariosModel> FN_ENTIDAD_A_MODELO = entidad -> Objects.isNull(entidad) ? null : new UsuariosModel(entidad);
   
    public UsuariosModel(Usuario entidad) {
        this.uuid = entidad.getUuid();
        this.nombre = entidad.getNombre();
        this.email = entidad.getEmail();
        this.estatus = EstadoUsuario.transformaModelo(entidad.getEstadoUsuario());
        this.passwordHash = entidad.getPasswordHash();
        this.creadoEn = entidad.getCreadoEn();
}

}
