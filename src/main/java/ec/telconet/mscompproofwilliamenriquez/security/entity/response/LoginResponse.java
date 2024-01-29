package ec.telconet.mscompproofwilliamenriquez.security.entity.response;

import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import lombok.Data;

@Data
public class LoginResponse {

    public UserResponse userResponse;

    public String token;

    public void mapData(UserResponse userResponse, String token) {
        this.userResponse = userResponse;
        this.token = token;
    }
}
