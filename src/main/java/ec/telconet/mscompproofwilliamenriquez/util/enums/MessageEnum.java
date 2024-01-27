package ec.telconet.mscompproofwilliamenriquez.util.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {
    OK("Respuesta exitosa.", 200),
    DELETE("Eliminación exitosa.", 200),
    UPDATE("Datos del usuario actualizado", 200),
    CREATE("Creación exitosa", 201),
    INTERNAL_ERROR("Problema en la transacción", 500),
    NOT_FOUND("No se encontraron resultados", 404),
    CORREO_NO_VALIDO("Ingrese un correo válido.", 403),
    UPPER_CASE("Ingrese el Nombre en mayúscula.", 403),
    NOT_STRONG_PASS("Su contraseña no es segura", 403),
    NOT_ENCRYPT("Hubo un conflicto", 409);;

    private String mensaje;
    private Integer code;

    MessageEnum(String mensaje, Integer code) {
        this.mensaje = mensaje;
        this.code = code;
    }
}
