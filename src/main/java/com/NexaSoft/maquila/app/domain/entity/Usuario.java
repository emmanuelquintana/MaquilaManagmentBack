package com.NexaSoft.maquila.app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import com.NexaSoft.maquila.app.web.model.UsuariosModel;
import com.NexaSoft.maquila.util.Estatus;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
/**
 * Entidad que representa un usuario.
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "estado_usuario_id", nullable = false)
    private EstadoUsuario estadoUsuario;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "creado_en", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp creadoEn;

    public Usuario() {
        this.uuid = UUID.randomUUID();
    }

    public static final Function<UsuariosModel, Usuario> FN_MODELO_A_ENTIDAD = usuarioModelo -> Objects.isNull(usuarioModelo) ? null : new Usuario( usuarioModelo);
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", estadoUsuario=" + estadoUsuario +
                ", creadoEn=" + creadoEn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) &&
                Objects.equals(uuid, usuario.uuid) &&
                Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(passwordHash, usuario.passwordHash) &&
                Objects.equals(estadoUsuario, usuario.estadoUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, uuid, nombre, passwordHash, estadoUsuario);
    }

    public Usuario(UsuariosModel usuarioModelo) {
        this.uuid = usuarioModelo.getUuid();
        this.nombre = usuarioModelo.getNombre();
        this.email = usuarioModelo.getEmail();
        this.passwordHash = usuarioModelo.getPasswordHash();
        this.estadoUsuario = new EstadoUsuario(Estatus.ACTIVO.getId(), Estatus.ACTIVO.getDescripcion());
    }
}
