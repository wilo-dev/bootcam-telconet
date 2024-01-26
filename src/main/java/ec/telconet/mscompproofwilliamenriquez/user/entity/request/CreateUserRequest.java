package ec.telconet.mscompproofwilliamenriquez.user.entity.request;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String usuario;
    private String clave;
    private String correo;
    private Character estado;
    private Boolean administrador;

    public CreateUserRequest() {

    }
}
