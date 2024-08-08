package com.NexaSoft.maquila.util;

public enum CodigosRespuesta {

    MM_US_001("Usuario creado exitosamente"),
    MM_US_002("Usuarios obtenidos exitosamente"),
    MM_US_003("Usuario obtenido exitosamente"),
    MM_US_004("Usuario actualizado exitosamente"),
    MM_US_005("Usuario eliminado exitosamente"),
    MM_US_006("El email del usuario ya existe"),
    MM_US_007("Estado del usuario no encontrado"),
    MM_US_008("Usuario no encontrado"),
    MM_US_009("Error al eliminar el usuario"),
    MM_US_010("Error al guardar el usuario"),
    MM_US_011("No se encontraron registros de usuarios"),
    MM_US_012("Error al obtener los usuarios"),
    MM_US_013("Error al actualizar el usuario"),   
    MM_EUS_001("Estado de usuario Consultado exitosamente"),
    MM_EUS_002("Estados de usuario obtenidos exitosamente"), 
    MM_GE_800("Dato Invalido"),
    MM_GE_999("Error No Controlado");

    private final String mensaje;

    CodigosRespuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public static CodigosRespuesta buscarPorCodigo(String codigo) {
        for (CodigosRespuesta enumerador : CodigosRespuesta.values()) {
            if (enumerador.name().equals(codigo)) {
                return enumerador;
            }
        }
        return MM_GE_999;
    }

    public final String mensaje() {
        return mensaje;
    }
}
