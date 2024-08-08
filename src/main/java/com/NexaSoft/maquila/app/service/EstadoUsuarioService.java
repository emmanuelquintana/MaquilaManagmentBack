package com.NexaSoft.maquila.app.service;

import com.NexaSoft.maquila.app.domain.entity.EstadoUsuario;
import com.NexaSoft.maquila.app.exception.BusinessException;
import com.NexaSoft.maquila.app.exception.NotFoundException;
import com.NexaSoft.maquila.app.facade.EstadoUsuarioFacade;
import com.NexaSoft.maquila.app.web.model.EstadoUsuarioModel;
import com.NexaSoft.maquila.util.CodigosRespuesta;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de estados de usuario.
 */
@Log4j2
@Service
public class EstadoUsuarioService {

    private final EstadoUsuarioFacade estadoUsuarioFacade;

    @Autowired
    public EstadoUsuarioService(EstadoUsuarioFacade estadoUsuarioFacade) {
        this.estadoUsuarioFacade = estadoUsuarioFacade;
    }

    /**
     * Obtiene todos los estados de usuario.
     *
     * @return una lista de modelos de estado de usuario
     */
    public List<EstadoUsuarioModel> obtenerTodosEstadosUsuario() {
        log.info("Obteniendo todos los estados de usuario");
        try {
            List<EstadoUsuario> estadosUsuario = estadoUsuarioFacade.obtenerTodosEstadosUsuario();
            if (estadosUsuario.isEmpty()) {
                log.warn("No se encontraron registros de estados de usuario");
                throw new NotFoundException(CodigosRespuesta.MM_US_011);
            }
            return estadosUsuario.stream().map(EstadoUsuario::transformaModelo).collect(Collectors.toList());
        } catch (DataAccessException e) {
            log.error("Error al obtener los estados de usuario", e);
            throw new BusinessException(CodigosRespuesta.MM_US_012);
        }
    }

    /**
     * Obtiene un estado de usuario por su ID.
     *
     * @param id el ID del estado de usuario
     * @return el modelo del estado de usuario
     */
    public EstadoUsuarioModel obtenerEstadoUsuarioPorId(Long id) {
        log.info("Obteniendo estado de usuario con ID: {}", id);
        try {
            return estadoUsuarioFacade.obtenerEstadoUsuarioPorId(id)
                    .map(EstadoUsuario::transformaModelo)
                    .orElseThrow(() -> {
                        log.warn("No se encontró el estado de usuario con ID: {}", id);
                        return new NotFoundException(CodigosRespuesta.MM_US_008);
                    });
        } catch (DataAccessException e) {
            log.error("Error al obtener el estado de usuario con ID: {}", id, e);
            throw new BusinessException(CodigosRespuesta.MM_US_012);
        }
    }
}
