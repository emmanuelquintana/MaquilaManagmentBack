package com.NexaSoft.maquila.app.domain.repository;

import com.NexaSoft.maquila.app.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio para la gestión de usuarios.
 */
@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    /**
     * Verifica si un usuario existe por su email.
     *
     * @param email el email del usuario
     * @return true si el usuario existe, false en caso contrario
     */
    boolean existsByEmail(String email);

    /**
     * Encuentra un usuario por su UUID.
     *
     * @param uuid el UUID del usuario
     * @return un Optional con el usuario encontrado
     */
    Optional<Usuario> findByUuid(UUID uuid);

    /**
     * Encuentra todos los usuarios con paginación.
     *
     * @param pageable la información de paginación
     * @return una página de usuarios
     */
    Page<Usuario> findAll(Pageable pageable);
}
