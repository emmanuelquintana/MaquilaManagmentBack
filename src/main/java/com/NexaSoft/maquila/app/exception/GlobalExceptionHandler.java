package com.NexaSoft.maquila.app.exception;

import com.NexaSoft.maquila.app.web.model.ResponseModel;
import com.NexaSoft.maquila.util.CodigosRespuesta;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.NexaSoft.maquila.util.Constantes.MENSAJE_ERROR_MODELO;
import static com.NexaSoft.maquila.util.Constantes.PRIMERA_POSICION;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Maneja las excepciones generadas validaciones de negocio
     */
    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<ResponseModel<Void>> handleBusinessExceptions(BusinessException exception, WebRequest request) {
        log.info("BusinessException ", exception);

        ResponseModel<Void> responseModel = ResponseModel.builder()
                .code(exception.getCodigosRespuesta())
                .data(null)
                .build();
        return ResponseEntity.badRequest()
                .body(responseModel);
    }


    /**
     * Maneja las excepciones generadas por recurso no encontrado
     */
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ResponseModel<Void>> handleNotFoundExceptions(NotFoundException exception, WebRequest request) {
        log.info("NotFoundException ", exception);

        ResponseModel<Void> responseModel = ResponseModel.builder()
                .code(exception.getCodigosRespuesta())
                .data(null)
                .build();
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(responseModel);
    }


    /**
     * Maneja las excepciones por errores no controlados del servicio
     */
    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<ResponseModel<Void>> handleServiceExceptions(ServiceException exception, WebRequest request) {
        log.warn("ServiceException ", exception);

        ResponseModel<Void> responseModel = ResponseModel.builder()
                .code(exception.getCodigosRespuesta())
                .data(null)
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseModel);
    }


    /**
     * Maneja las excepciones no controladas por la aplicación.
     */
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ResponseModel<Void>> manejarExcepcionesInternas(Exception exception, WebRequest request) {
        log.warn("Excepcion general ", exception);

        ResponseModel<Void> responseModel = ResponseModel.builder()
                .code(CodigosRespuesta.MM_GE_999, exception.getMessage())
                .data(null)
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseModel);
    }


    /**
     * Formatea las respuestas que no generan una exception
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Manejador de excepciones", exception);
        CodigosRespuesta responseCode = CodigosRespuesta.MM_GE_999;
        String message = exception.getMessage();

        if (exception instanceof MethodArgumentNotValidException) {
            return manejarExcepcionesDeModelos((MethodArgumentNotValidException) exception);
        }

        if (exception instanceof HttpMessageNotReadableException) {
            responseCode = CodigosRespuesta.MM_GE_800;
        }


        ResponseModel<Void> responseModel = ResponseModel.builder()
                .code(responseCode, message)
                .data(body)
                .build();
        return ResponseEntity
                .status(status)
                .body(responseModel);
    }

    protected ResponseEntity<Object> manejarExcepcionesDeModelos(MethodArgumentNotValidException exception) {
        log.info("Excepcion de modelos ", exception);

        List<String> errores = exception.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        CodigosRespuesta responseCode = CodigosRespuesta.buscarPorCodigo(errores.get(PRIMERA_POSICION));

        ResponseModel<Void> responseModel = ResponseModel.builder()
                .code(responseCode)
                .data(responseCode.mensaje())
                .build();
        responseModel.setMessage(MENSAJE_ERROR_MODELO);

        return ResponseEntity.badRequest().body(responseModel);
}

}