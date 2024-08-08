package com.NexaSoft.maquila.app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.NexaSoft.maquila.app.web.model.EstadoUsuarioModel;
import com.NexaSoft.maquila.util.Estatus;

import java.util.Objects;

/**
 * Entidad que representa el estado de un usuario.
 */
@Entity
@Table(name = "estados_usuario")
@Getter
@Setter
@NoArgsConstructor// Asegúrate de tener un constructor sin argumentos

public class EstadoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String descripcion;

    @Override
    public String toString() {
        return "EstadoUsuario{" +
                "id=" + id +
                ", nombre='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoUsuario that = (EstadoUsuario) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    public EstadoUsuario(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public static EstadoUsuarioModel transformaModelo(EstadoUsuario entidad) {
        return EstadoUsuarioModel.builder().id(entidad.getId()).descripcion(entidad.getDescripcion()).build();
} 
 public Boolean getEstatus() {
    return Objects.equals(this.id, Estatus.ACTIVO.getId());

}
}
