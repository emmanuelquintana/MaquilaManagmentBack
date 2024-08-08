package com.NexaSoft.maquila.app.exception;

import com.NexaSoft.maquila.util.CodigosRespuesta;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    @Getter
    private final CodigosRespuesta codigosRespuesta;

    public ConflictException(CodigosRespuesta codigosRespuesta) {
        super(codigosRespuesta.mensaje());
        this.codigosRespuesta = codigosRespuesta;
}
}

