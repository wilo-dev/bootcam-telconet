package ec.telconet.mscompproofwilliamenriquez.user.service;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.userRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.user.repository.UserRepository;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import ec.telconet.mscompproofwilliamenriquez.util.enums.MessageEnum;
import ec.telconet.mscompproofwilliamenriquez.util.exception.MyException;
import ec.telconet.mscompproofwilliamenriquez.util.helper.MethodHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${ec.message}")
    private String perfil;

    @Value("${ec.secret-key-password}")
    private String secretKeyPassword;

    @Autowired
    private UserRepository userRepository;

    public OutputEntity<List<UserResponse>> getAllUsers() {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            List<UserEntity> userModel = userRepository.findAll();
            if (userModel.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<UserResponse> usuarioResponses = userModel.stream().map(UserResponse::new).collect(Collectors.toList());

            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<UserResponse>> getAllEnabledUsers() {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            List<UserEntity> userModel = userRepository.findByEstado('a');
            if (userModel.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<UserResponse> usuarioResponses = userModel.stream().map(UserResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<UserResponse> getUser(Long id) {
        OutputEntity<UserResponse> outPut = new OutputEntity<>();
        try {
            Optional<UserEntity> userModel = userRepository.findByIdAndEstado(id, 'a');
            if (userModel.isPresent()) {
                UserResponse userResponse = new UserResponse(userModel.get());
                return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), userResponse);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }


//    public OutputEntity<UserResponse> getUser(Long id) {
//        OutputEntity<UserResponse> outPut = new OutputEntity<>();
//        try {
//            Optional<UserEntity> userModel = userRepository.findById(id);
//            if (userModel.isPresent()) {
//                UserResponse userResponse = new UserResponse(userModel.get());
//                return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), userResponse);
//            } else {
//                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
//            }
//        } catch (MyException e) {
//            return outPut.error(e.getCode(), e.getMessages(), null);
//        } catch (DataAccessException e) {
//            return outPut.error();
//        }
//    }

    public OutputEntity<String> createUser(userRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            // TODO: validar correo
            if (!MethodHelper.isValidEmail(data.getCorreo())) {
                throw new MyException(MessageEnum.CORREO_NO_VALIDO.getCode(), MessageEnum.CORREO_NO_VALIDO.getMensaje());
            }
            // TODO: nombre sea en mayuscula
            MethodHelper.isUpperCase(data.getNombreUsuario());
            // TODO: clave segura
            MethodHelper.isStronge(data.getClave());
            // TODO: encrytar clave
            data.setClave(MethodHelper.encryptPassword(data.getClave(), secretKeyPassword));

            UserEntity userEntity = new UserEntity(data);
            // TODO: guardamos
            userRepository.save(userEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> deleteUser(Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<UserEntity> existeUser = userRepository.findById(id);
            if (existeUser.isPresent()) {
                UserEntity userStatus = existeUser.get();
                userStatus.setEstado('e');
                userRepository.save(userStatus);
                return outPut.ok(MessageEnum.DELETE.getCode(), MessageEnum.DELETE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> updateUser(userRequest data, Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<UserEntity> existeUser = userRepository.findById(id);

            if (existeUser.isPresent()) {
                // TODO: validar correo
                if (!MethodHelper.isValidEmail(data.getCorreo())) {
                    throw new MyException(MessageEnum.CORREO_NO_VALIDO.getCode(), MessageEnum.CORREO_NO_VALIDO.getMensaje());
                }
                // TODO: nombre sea en mayuscula
                MethodHelper.isUpperCase(data.getNombreUsuario());
                // TODO: clave segura
                MethodHelper.isStronge(data.getClave());
                // TODO: encrytar clave
                data.setClave(MethodHelper.encryptPassword(data.getClave(), secretKeyPassword));
                // TODO: actualizando userEntity
                UserEntity userEntity = existeUser.get();
                userEntity.actualizarDatos(data);
                userRepository.save(userEntity);
                return outPut.ok(MessageEnum.UPDATE.getCode(), MessageEnum.UPDATE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

}
