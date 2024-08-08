package com.NexaSoft.maquila.app.service;

import com.NexaSoft.maquila.app.domain.entity.EstadoUsuario;
import com.NexaSoft.maquila.app.domain.entity.Usuario;
import com.NexaSoft.maquila.app.exception.BusinessException;
import com.NexaSoft.maquila.app.exception.ConflictException;
import com.NexaSoft.maquila.app.exception.NotFoundException;
import com.NexaSoft.maquila.app.facade.EstadoUsuarioFacade;
import com.NexaSoft.maquila.app.facade.UsuariosFacade;
import com.NexaSoft.maquila.app.web.model.UsuariosModel;
import com.NexaSoft.maquila.util.CodigosRespuesta;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la gestión de usuarios.
 */
@Log4j2
@Service
public class UsuariosService {

    private final UsuariosFacade usuariosFacade;
    private final EstadoUsuarioFacade estadoUsuarioFacade;

    @Autowired
    public UsuariosService(UsuariosFacade usuariosFacade, EstadoUsuarioFacade estadoUsuarioFacade) {
        this.usuariosFacade = usuariosFacade;
        this.estadoUsuarioFacade = estadoUsuarioFacade;
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuarioModel el modelo del usuario a crear
     * @return el UUID del usuario creado
     */
    public UUID crearUsuario(UsuariosModel usuarioModel) {
        log.info("Creando usuario con email: {}", usuarioModel.getEmail());

        if (usuariosFacade.existeUsuarioPorEmail(usuarioModel.getEmail())) {
            log.warn("El email del usuario ya existe: {}", usuarioModel.getEmail());
            throw new ConflictException(CodigosRespuesta.MM_US_006);
        }

        Optional<EstadoUsuario> estadoUsuarioOpt = estadoUsuarioFacade.obtenerEstadoUsuarioPorId(usuarioModel.getEstatus().getId());
        if (estadoUsuarioOpt.isEmpty()) {
            log.warn("Estado del usuario no encontrado con ID: {}", usuarioModel.getEstatus().getId());
            throw new NotFoundException(CodigosRespuesta.MM_US_007);
        }

        Usuario usuario = new Usuario(usuarioModel);
        usuario.setEstadoUsuario(estadoUsuarioOpt.get());

        try {
            usuariosFacade.guardarUsuario(usuario);
        } catch (DataAccessException e) {
            log.error("Error al guardar el usuario con email: {}", usuarioModel.getEmail(), e);
            throw new BusinessException(CodigosRespuesta.MM_US_010);
        }

        log.info("Usuario creado exitosamente con UUID: {}", usuario.getUuid());
        return usuario.getUuid();
    }

    /**
     * Obtiene todos los usuarios con paginación.
     *
     * @param pageable la información de paginación
     * @return una página de modelos de usuario
     */
    public Page<UsuariosModel> obtenerTodosUsuarios(Pageable pageable) {
        log.info("Obteniendo todos los usuarios");
        try {
            Page<Usuario> usuarios = usuariosFacade.obtenerTodosUsuarios(pageable);
            if (usuarios.isEmpty()) {
                log.warn("No se encontraron registros de usuarios");
                throw new NotFoundException(CodigosRespuesta.MM_US_011);
            }
            return usuarios.map(UsuariosModel::new);
        } catch (DataAccessException e) {
            log.error("Error al obtener los usuarios", e);
            throw new BusinessException(CodigosRespuesta.MM_US_012);
        }
    }

    /**
     * Obtiene un usuario por su UUID.
     *
     * @param uuid el UUID del usuario
     * @return el modelo del usuario
     */
    public UsuariosModel obtenerUsuarioPorUuid(UUID uuid) {
        log.info("Obteniendo usuario con UUID: {}", uuid);
        try {
            return usuariosFacade.obtenerUsuarioPorUuid(uuid)
                    .map(UsuariosModel::new)
                    .orElseThrow(() -> {
                        log.warn("No se encontró el usuario con UUID: {}", uuid);
                        return new NotFoundException(CodigosRespuesta.MM_US_008);
                    });
        } catch (DataAccessException e) {
            log.error("Error al obtener el usuario con UUID: {}", uuid, e);
            throw new BusinessException(CodigosRespuesta.MM_US_012);
        }
    }

    /**
     * Actualiza un usuario.
     *
     * @param uuid         el UUID del usuario a actualizar
     * @param usuarioModel el modelo del usuario con los datos actualizados
     * @return el UUID del usuario actualizado
     */
    public UUID actualizarUsuario(UUID uuid, UsuariosModel usuarioModel) {
        log.info("Actualizando usuario con UUID: {}", uuid);
        Usuario usuario = usuariosFacade.obtenerUsuarioPorUuid(uuid)
                .orElseThrow(() -> new NotFoundException(CodigosRespuesta.MM_US_008));

        Optional<EstadoUsuario> estadoUsuarioOpt = estadoUsuarioFacade.obtenerEstadoUsuarioPorId(usuarioModel.getEstatus().getId());
        if (estadoUsuarioOpt.isEmpty()) {
            log.warn("Estado del usuario no encontrado con ID: {}", usuarioModel.getEstatus().getId());
            throw new NotFoundException(CodigosRespuesta.MM_US_007);
        }

        usuario.setNombre(usuarioModel.getNombre());
        usuario.setEmail(usuarioModel.getEmail());
        usuario.setEstadoUsuario(estadoUsuarioOpt.get());
        usuario.setPasswordHash(usuarioModel.getPasswordHash());

        try {
            usuariosFacade.guardarUsuario(usuario);
        } catch (DataAccessException e) {
            log.error("Error al actualizar el usuario con UUID: {}", uuid, e);
            throw new BusinessException(CodigosRespuesta.MM_US_013);
        }

        log.info("Usuario actualizado exitosamente con UUID: {}", usuario.getUuid());
        return usuario.getUuid();
    }

    /**
     * Marca un usuario como inactivo.
     *
     * @param uuid         el UUID del usuario a eliminar
     * @param usuarioModel el modelo del usuario con los datos actualizados
     * @return el UUID del usuario eliminado
     */
    public UUID eliminarUsuario(UUID uuid, UsuariosModel usuarioModel) {
        log.info("Eliminando usuario con UUID: {}", uuid);
        Usuario usuario = usuariosFacade.obtenerUsuarioPorUuid(uuid)
                .orElseThrow(() -> new NotFoundException(CodigosRespuesta.MM_US_008));

        Optional<EstadoUsuario> estadoUsuarioOpt = estadoUsuarioFacade.obtenerEstadoUsuarioPorId(usuarioModel.getEstatus().getId());
        if (estadoUsuarioOpt.isEmpty()) {
            log.warn("Estado del usuario no encontrado con ID: {}", usuarioModel.getEstatus().getId());
            throw new NotFoundException(CodigosRespuesta.MM_US_007);
        }

        usuario.setEstadoUsuario(estadoUsuarioOpt.get());

        try {
            usuariosFacade.guardarUsuario(usuario);
        } catch (DataAccessException e) {
            log.error("Error al eliminar el usuario con UUID: {}", uuid, e);
            throw new BusinessException(CodigosRespuesta.MM_US_009);
        }

        log.info("Usuario eliminado exitosamente con UUID: {}", usuario.getUuid());
        return usuario.getUuid();
    }
}
