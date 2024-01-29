package ec.telconet.mscompproofwilliamenriquez.security.service;

import ec.telconet.mscompproofwilliamenriquez.security.entity.request.LoginRequest;
import ec.telconet.mscompproofwilliamenriquez.security.entity.response.LoginResponse;
import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.user.repository.UserRepository;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import ec.telconet.mscompproofwilliamenriquez.util.enums.MessageEnum;
import ec.telconet.mscompproofwilliamenriquez.util.exception.MyException;
import ec.telconet.mscompproofwilliamenriquez.util.helper.MethodHelper;
import ec.telconet.mscompproofwilliamenriquez.util.helper.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LoginService {

    @Value("${ec.secret-key-password}")
    private String secretKeyPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenHelper tokenHelper;

    public OutputEntity<LoginResponse> login(LoginRequest data) {
        OutputEntity<LoginResponse> outPut = new OutputEntity<>();
        try {
            LoginResponse login = new LoginResponse();
            UserEntity usuarioModelo = this.userRepository.findByUserLogin(data.getUsuario());

            if (usuarioModelo == null) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }

            // TODO: COMPRAR LA CLAVE
            String password = MethodHelper.desencryptPass(usuarioModelo.getClave(), this.secretKeyPassword);
            UserResponse usuario = new UserResponse(usuarioModelo);

            if (!StringUtils.pathEquals(password, data.getClave())) {
                throw new MyException(MessageEnum.LOGIN_ERROR.getCode(), MessageEnum.LOGIN_ERROR.getMensaje());
            }
            login.setUserResponse(usuario);
            login.setToken(this.tokenHelper.generateToken(data.getUsuario(), usuario, "clave123"));

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), login);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessage(), null);
        } catch (Exception e) {
            return outPut.error();
        }

    }


}
