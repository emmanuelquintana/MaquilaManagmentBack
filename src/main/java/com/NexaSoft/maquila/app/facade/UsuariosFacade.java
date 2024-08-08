package com.NexaSoft.maquila.app.facade;

import com.NexaSoft.maquila.app.domain.entity.Usuario;
import com.NexaSoft.maquila.app.domain.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

/**
 * Facade para la gestión de usuarios.
 */
@Component
public class UsuariosFacade {

    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosFacade(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param usuario el usuario a guardar
     */
    public void guardarUsuario(Usuario usuario) {
        usuariosRepository.saveAll(List.of(usuario));
    }

    /**
     * Obtiene todos los usuarios con paginación.
     *
     * @param pageable la información de paginación
     * @return una página de usuarios
     */
    public Page<Usuario> obtenerTodosUsuarios(Pageable pageable) {
        return usuariosRepository.findAll(pageable);
    }

    /**
     * Obtiene un usuario por su UUID.
     *
     * @param uuid el UUID del usuario
     * @return un Optional con el usuario encontrado
     */
    public Optional<Usuario> obtenerUsuarioPorUuid(UUID uuid) {
        return usuariosRepository.findByUuid(uuid);
    }

    /**
     * Verifica si un usuario existe por su email.
     *
     * @param email el email del usuario
     * @return true si el usuario existe, false en caso contrario
     */
    public boolean existeUsuarioPorEmail(String email) {
        return usuariosRepository.existsByEmail(email);
    }
}
