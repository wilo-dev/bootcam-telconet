package ec.telconet.mscompproofwilliamenriquez.security.entity.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String usuario;
    private String clave;
}
