package com.NexaSoft.maquila.app.exception;
import com.NexaSoft.maquila.util.CodigosRespuesta;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    @Getter
    private final CodigosRespuesta codigosRespuesta;

    public NotFoundException(CodigosRespuesta responseCode) {
        super(responseCode.mensaje());
        this.codigosRespuesta = responseCode;
}
}
