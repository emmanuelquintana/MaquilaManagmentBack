package com.NexaSoft.maquila.app.web.controller;

import com.NexaSoft.maquila.app.service.EstadoUsuarioService;
import com.NexaSoft.maquila.app.web.model.EstadoUsuarioModel;
import com.NexaSoft.maquila.app.web.model.ResponseModel;
import com.NexaSoft.maquila.util.CodigosRespuesta;
import com.NexaSoft.maquila.util.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar los estados de usuario.
 */
@RestController
@Api(value = "EstadoUsuarioController", tags = ("Api EstadoUsuario"))
@RequestMapping(value = Constantes.PATH_ESTADOS_USUARIO)
public class EstadoUsuarioController {

    private final EstadoUsuarioService estadoUsuarioService;

    @Autowired
    public EstadoUsuarioController(EstadoUsuarioService estadoUsuarioService) {
        this.estadoUsuarioService = estadoUsuarioService;
    }

    @ApiOperation(value = "Obtener todos los estados de usuario", notes = "Endpoint para obtener todos los estados de usuario")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseModel<List<EstadoUsuarioModel>>> obtenerTodosEstadosUsuario() {
        List<EstadoUsuarioModel> estadosUsuario = estadoUsuarioService.obtenerTodosEstadosUsuario();
        return ResponseEntity.ok(ResponseModel.builder().code(CodigosRespuesta.MM_EUS_002).data(estadosUsuario).build());
    }

    @ApiOperation(value = "Obtener un estado de usuario por ID", notes = "Endpoint para obtener un estado de usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(code = Constantes.CODE_NOT_FOUND, message = "No se encontró la información"),
            @ApiResponse(code = Constantes.CODE_INTERNAL_ERROR, message = "Error no controlado"),
            @ApiResponse(code = Constantes.CODE_BAD_REQUEST, message = "Petición errónea"),
            @ApiResponse(code = Constantes.CODE_UNAUTHORIZED, message = "No autorizado"),
            @ApiResponse(code = Constantes.CODE_FORBIDDEN, message = "Prohibido")
    })
    @GetMapping(value = Constantes.SEPARATOR + Constantes.ID, produces = "application/json")
    public ResponseEntity<ResponseModel<EstadoUsuarioModel>> obtenerEstadoUsuarioPorId(@PathVariable Long id) {
        EstadoUsuarioModel estadoUsuario = estadoUsuarioService.obtenerEstadoUsuarioPorId(id);
        return ResponseEntity.ok(ResponseModel.builder().code(CodigosRespuesta.MM_EUS_001).data(estadoUsuario).build());
    }
}
