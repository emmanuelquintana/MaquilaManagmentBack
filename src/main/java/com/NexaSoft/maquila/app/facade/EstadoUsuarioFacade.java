package com.NexaSoft.maquila.app.facade;

import com.NexaSoft.maquila.app.domain.entity.EstadoUsuario;
import com.NexaSoft.maquila.app.domain.repository.EstadoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Facade para la gestión de estados de usuario.
 */
@Component
public class EstadoUsuarioFacade {

    private final EstadoUsuarioRepository estadoUsuarioRepository;

    @Autowired
    public EstadoUsuarioFacade(EstadoUsuarioRepository estadoUsuarioRepository) {
        this.estadoUsuarioRepository = estadoUsuarioRepository;
    }

    /**
     * Obtiene un estado de usuario por su ID.
     *
     * @param id el ID del estado de usuario
     * @return un Optional con el estado de usuario encontrado
     */
    public Optional<EstadoUsuario> obtenerEstadoUsuarioPorId(Long id) {
        return estadoUsuarioRepository.findById(id);
    }

    /**
     * Obtiene todos los estados de usuario.
     *
     * @return una lista de estados de usuario
     */
    public List<EstadoUsuario> obtenerTodosEstadosUsuario() {
        return estadoUsuarioRepository.findAll();
    }
}
