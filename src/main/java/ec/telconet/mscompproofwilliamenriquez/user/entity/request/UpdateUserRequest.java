package ec.telconet.mscompproofwilliamenriquez.user.entity.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserRequest {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String usuario;
    private String clave;
    private String correo;
    private Character estado;
    private Boolean administrador;

    public UpdateUserRequest(String nombreUsuario, String apellidoUsuario, String usuario, String clave, String correo, Character estado, Boolean administrador) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.correo = correo;
        this.estado = estado;
        this.administrador = administrador;
    }
}
