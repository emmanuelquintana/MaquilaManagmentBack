package com.NexaSoft.maquila.app.web.model;

import brave.Tracer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import springfox.documentation.spring.web.json.Json;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import com.NexaSoft.maquila.util.Constantes.*;
import com.NexaSoft.maquila.util.CodigosRespuesta;
import com.NexaSoft.maquila.util.Constantes;

@ApiModel(description = "Modelo de respuestas")
public final class ResponseModel<T> {
    @NonNull
    @Getter
    @ApiModelProperty(value = "Código de negocio con la nomenclatura XX_YY_### donde, XX=Identificador del módulo, YY=Identificador del microservicio y ###=Número consecutivo", required = true, example = "AT_TS_001")
    private String code;

    @NonNull
    @Getter
    @Setter
    @ApiModelProperty(value = "Mensaje de negocio", required = true, example = "Consulta exitosa")
    private String message;

    @NonNull
    @Getter
    @ApiModelProperty(value = "Identificado de la traza", required = true, example = "a3807e9f2ec0d8ad")
    private String traceId;

    @Nullable
    @Getter
    @ApiModelProperty(value = "Modelo datos del recurso consultado(puede ser una lista)", required = true, example = "{}")
    private T data;

    @Nullable
    @Getter
    @ApiModelProperty(value = "Datos adicionales para describir la respuesta", required = true)
    private Metadata metadata;

    private Tracer tracer;

    private ResponseModel() {
        //Implementación por default, se agrega comentario por solicitud de sonar.
    }

    public static ICode builder() {
        return new ResponseModel.Builder();
    }

    public interface ICode {
        IData code(CodigosRespuesta code, String... values);
    }

    public interface IData<T> {
        IBuilder data(T data);
    }

    public interface IBuilder {
        ResponseModel build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    private static class Metadata {
        @ApiModelProperty(value = "Número de la página consultada", required = true, example = "1")
        private int page;
        @ApiModelProperty(value = "Tamaño de la página consultada", required = true, example = "10")
        private int size;
        @ApiModelProperty(value = "Total de elementos existentes", required = true, example = "45")
        private long elements;
    }

    private static class Builder implements ICode, IData, IBuilder {
        private final ResponseModel instance = new ResponseModel();

        @Override
        public IData code(CodigosRespuesta code, String... values) {
            instance.code = code.name();
            StringBuilder message = new StringBuilder(code.mensaje());
            if (values.length > Constantes.NUMERO_CERO) {
                message.append(Constantes.ABRE_PARENTESIS);
                message.append(Arrays.asList(values)
                        .stream()
                        .collect(Collectors.joining(Constantes.COMA)));
                message.append(Constantes.CIERRA_PARENTESIS);
            }
            instance.message = message.toString();
            return this;
        }

        @Override
        public IBuilder data(Object data) {
            instance.metadata = new Metadata();
            if (Objects.isNull(data)) {
                instance.data = new Json(Constantes.LLAVES_JSON);
            } else if (data instanceof Page) {
                Page page = (Page) data;
                instance.data = page.getContent();
                instance.metadata = new Metadata(page.getNumber(), page.getNumberOfElements(), page.getTotalElements());
            } else {
                instance.data = data;
            }

            return this;
        }

        @Override
        public ResponseModel build() {
            return instance;
        }
    }
}