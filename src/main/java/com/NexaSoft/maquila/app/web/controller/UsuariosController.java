package com.NexaSoft.maquila.app.web.controller;

import com.NexaSoft.maquila.app.service.UsuariosService;
import com.NexaSoft.maquila.app.web.model.UsuariosModel;
import com.NexaSoft.maquila.app.web.model.ResponseModel;
import com.NexaSoft.maquila.util.CodigosRespuesta;
import com.NexaSoft.maquila.util.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

/**
 * Controlador para gestionar los usuarios.
 */
@RestController
@Api(value = "UsuariosController", tags = ("Api Usuarios"))
@RequestMapping(value = Constantes.PATH_USUARIOS)
public class UsuariosController {

    private final UsuariosService usuariosService;

    @Autowired
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @ApiOperation(value = "Crear un nuevo usuario", notes = "Endpoint para crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_CREATED, message = "Petición recibida"),
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<ResponseModel<UUID>> crearUsuario(@RequestBody UsuariosModel usuarioModel) {
        UUID uuid = usuariosService.crearUsuario(usuarioModel);
        return ResponseEntity.created(URI.create(Constantes.PATH_USUARIOS + Constantes.SEPARATOR + uuid)).body(
                ResponseModel.builder().code(CodigosRespuesta.MM_US_001).data(uuid).build()
        );
    }

    @ApiOperation(value = "Obtener todos los usuarios", notes = "Endpoint para obtener todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseModel<Page<UsuariosModel>>> obtenerTodosUsuarios(Pageable pageable) {
        Page<UsuariosModel> usuarios = usuariosService.obtenerTodosUsuarios(pageable);
        return ResponseEntity.ok(ResponseModel.builder().code(CodigosRespuesta.MM_US_002).data(usuarios).build());
    }

    @ApiOperation(value = "Obtener un usuario por UUID", notes = "Endpoint para obtener un usuario por UUID")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @GetMapping(value = Constantes.SEPARATOR + Constantes.ID, produces = "application/json")
    public ResponseEntity<ResponseModel<UsuariosModel>> obtenerUsuarioPorUuid(@PathVariable UUID uuid) {
        UsuariosModel usuario = usuariosService.obtenerUsuarioPorUuid(uuid);
        return ResponseEntity.ok(ResponseModel.builder().code(CodigosRespuesta.MM_US_003).data(usuario).build());
    }

    @ApiOperation(value = "Actualizar un usuario", notes = "Endpoint para actualizar un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @PutMapping(value = Constantes.SEPARATOR + Constantes.ID, produces = "application/json")
    public ResponseEntity<ResponseModel<UUID>> actualizarUsuario(@PathVariable UUID uuid, @RequestBody UsuariosModel usuarioModel) {
        UUID updatedUuid = usuariosService.actualizarUsuario(uuid, usuarioModel);
        return ResponseEntity.ok(ResponseModel.builder().code(CodigosRespuesta.MM_US_004).data(updatedUuid).build());
    }

    @ApiOperation(value = "Eliminar un usuario", notes = "Endpoint para eliminar un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @DeleteMapping(value = Constantes.SEPARATOR + Constantes.ID, produces = "application/json")
    public ResponseEntity<ResponseModel<UUID>> eliminarUsuario(@PathVariable UUID uuid, @RequestBody UsuariosModel usuarioModel) {
        UUID deletedUuid = usuariosService.eliminarUsuario(uuid, usuarioModel);
        return ResponseEntity.ok(ResponseModel.builder().code(CodigosRespuesta.MM_US_005).data(deletedUuid).build());
    }
}
