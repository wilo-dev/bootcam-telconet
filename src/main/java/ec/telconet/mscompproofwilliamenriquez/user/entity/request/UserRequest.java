package ec.telconet.mscompproofwilliamenriquez.user.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String usuario;
    private String clave;
    private String correo;
}
