package ec.telconet.mscompproofwilliamenriquez.user.service;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.UserRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.user.repository.UserRepository;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import ec.telconet.mscompproofwilliamenriquez.util.enums.MessageEnum;
import ec.telconet.mscompproofwilliamenriquez.util.exception.MyException;
import ec.telconet.mscompproofwilliamenriquez.util.helper.MethodHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createUser(UserRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            // TODO: validar correo
            if (!MethodHelper.isValidEmail(data.getCorreo())) {
                throw new MyException(MessageEnum.CORREO_NO_VALIDO.getCode(), MessageEnum.CORREO_NO_VALIDO.getMensaje());
            }
            // TODO: validar username sea unico
            List<UserEntity> userList = userRepository.findByUser(data.getUsuario());
            if (!userList.isEmpty()) {
                throw new MyException(MessageEnum.USERNAME_UNICO.getCode(), MessageEnum.USERNAME_UNICO.getMensaje());
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

    public OutputEntity<String> updateUser(UserRequest data, Long id) {
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

//    public OutputEntity<List<UserResponse>> getlastUser(Integer paginaInicial, Integer paginaFinal){
//        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
//        try{
//            Pageable pageable = Page
//        }catch (){}
//    }


    public OutputEntity<List<UserResponse>> findByName(String name) {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            List<UserEntity> userModel = userRepository.findByName(name);
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

    public OutputEntity<List<UserResponse>> findByUser(String user) {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            List<UserEntity> userModel = userRepository.findByUser(user);
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

    public OutputEntity<List<UserResponse>> findByMail(String email) {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            List<UserEntity> userModel = userRepository.findByMail(email);
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

    public OutputEntity<List<UserResponse>> getLastUser(Integer paginaInicial, Integer paginaFinal) {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            Pageable pageable = PageRequest.of(paginaInicial, paginaFinal, Sort.by("id").descending());
            List<UserEntity> usuarioModelo = this.userRepository.findAll(pageable).getContent();

            if (usuarioModelo.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<UserResponse> usuarioResponses = usuarioModelo.stream().map(u -> new UserResponse(u))
                    .collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), usuarioResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }
}
