package ec.telconet.mscompproofwilliamenriquez.user.entity.response;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import lombok.Data;

@Data
public class UserResponse {
    private Long identificador;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String usuario;
    private String correo;
    private String estado;
    private Boolean administrador;

    public UserResponse(UserEntity u) {
        this.identificador = u.getId();
        this.nombreUsuario = u.getNombre();
        this.apellidoUsuario = u.getApellido();
        this.usuario = u.getUsuario();
        this.correo = u.getCorreo();
        this.administrador = u.getAdministrator();

        switch (u.getEstado().toString().toLowerCase()) {
            case "a":
                this.estado = "Activo";
                break;
            case "e":
                this.estado = "Eliminado";
                break;
            case "i":
                this.estado = "Inactivo";
                break;
        }
    }
}
