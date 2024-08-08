package com.NexaSoft.maquila.util;

public final class Constantes {
    private Constantes() {

    }

    //paths
    public static final String PATH_V1 = "/v1";
    public static final String PATH_USUARIOS = PATH_V1 + "/usuarios";
    public static final String PATH_ESTADOS_USUARIO = PATH_V1 + "/estados-usuario";
    public static final String SEPARATOR = "/";
    public static final String ID = "/{id}";
    public static final String UUID = "/{uuid}";

    public static final String PATH_CLIENTES = "/v1/clientes";
    public static final String PATH_MAQUILADORES = "/v1/maquiladores";
    public static final String PATH_PRODUCTOS = "/v1/productos";
    public static final String PATH_VARIANTES = "/v1/variantes";
    public static final String PATH_ORDENES_ENTRADA = "/v1/ordenes-entrada";
    public static final String PATH_ORDENES_ENTRADA_PRODUCTOS = "/v1/ordenes-entrada-productos";
    public static final String PATH_ORDENES_SALIDA = "/v1/ordenes-salida";
    public static final String PATH_ORDENES_SALIDA_PRODUCTOS = "/v1/ordenes-salida-productos";
    public static final String PATH_SKU_MARKETPLACE = "/v1/sku-marketplace";
    public static final String PATH_INVENTARIO_MARKETPLACE = "/v1/inventario-marketplace";
    public static final String PATH_COTIZACIONES = "/v1/cotizaciones";
    public static final String PATH_DETALLE_COTIZACIONES = "/v1/detalle-cotizaciones";
    public static final String PATH_HOJAS_CORTE = "/v1/hojas-corte";


    //CODE_HTTP
    public static final int CODE_CREATED = 201;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_FORBIDDEN = 403;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_INTERNAL_ERROR = 500;
    public static final int CODE_BAD_REQUEST = 400;

    public static final int NUMERO_CERO = 0;
    public static final String ABRE_PARENTESIS = "(";
    public static final String CIERRA_PARENTESIS = ")";
    public static final String COMA = ",";
    public static final String LLAVES_JSON = "{}";


    public static final int PRIMERA_POSICION = 0;
    public static final String MENSAJE_ERROR_MODELO = "Error al validar modelo de solicitud.";

}
