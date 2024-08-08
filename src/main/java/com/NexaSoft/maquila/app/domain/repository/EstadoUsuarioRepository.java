package com.NexaSoft.maquila.app.domain.repository;

import com.NexaSoft.maquila.app.domain.entity.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;
/**
 * Repositorio para la gestión de estados de usuario.
 */
@Repository

public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario, Long> {

    /**
     * Encuentra un estado de usuario por su id.
     * 
     * @param id el id del estado de usuario
     * @return un Optional con el estado de usuario encontrado
     * 
     * */
    
    Optional<EstadoUsuario> findById(Long id);

    /**
     * Obtener todos los estados de usuario.
     * 
     * @return una lista de todos los estados de usuario
     * 
     * */
    List<EstadoUsuario> findAll();
}
