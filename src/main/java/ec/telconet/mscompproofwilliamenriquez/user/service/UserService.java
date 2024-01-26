package ec.telconet.mscompproofwilliamenriquez.user.service;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.CreateUserRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.UpdateUserRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.user.repository.UserRepository;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public OutputEntity<List<UserResponse>> getAllUsers() {
        OutputEntity<List<UserResponse>> outPut = new OutputEntity<>();
        try {
            List<UserEntity> userModel = this.userRepository.findAll();
            List<UserResponse> usuarioResponses = userModel.stream().map(UserResponse::new).collect(Collectors.toList());

            return outPut.ok(200, "OK", usuarioResponses);
        } catch (Exception e) {
            return outPut.error(500, "Error de Sistema", null);
        }
    }

    public OutputEntity<UserResponse> getUser(Long id) {
        OutputEntity<UserResponse> outPut = new OutputEntity<>();
        try {
            Optional<UserEntity> userModel = userRepository.findById(id);
            if (userModel.isPresent()) {
                UserResponse userResponse = new UserResponse(userModel.get());
                return outPut.ok(200, "OK", userResponse);
            } else {
                return outPut.error(404, "Usuario no encontrado", null);
            }
        } catch (Exception e) {
            return outPut.error(500, "Error de Sistema", null);
        }
    }

    public UserEntity createUser(CreateUserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNombre(userRequest.getNombreUsuario());
        userEntity.setApellido(userRequest.getApellidoUsuario());
        userEntity.setUsuario(userRequest.getUsuario());
        userEntity.setClave(userRequest.getClave());
        userEntity.setCorreo(userRequest.getCorreo());
        userEntity.setEstado(userRequest.getEstado());
        userEntity.setAdministrator(userRequest.getAdministrador());
        return userRepository.save(userEntity);
    }

    public OutputEntity<UserEntity> updatedUser(Long id, UpdateUserRequest userRequest) {
        OutputEntity<UserEntity> outPut = new OutputEntity<>();
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity = optionalUserEntity.get();
                userEntity.setNombre(userRequest.getNombreUsuario());
                userEntity.setApellido(userRequest.getApellidoUsuario());
                userEntity.setUsuario(userRequest.getUsuario());
                userEntity.setClave(userRequest.getClave());
                userEntity.setCorreo(userRequest.getCorreo());
                userEntity.setEstado(userRequest.getEstado());
                userEntity.setAdministrator(userRequest.getAdministrador());
                userEntity.setUpdatedAt(new Date());
                userRepository.save(userEntity);
                return outPut.error(200, "OK", userEntity);
            } else {
                return outPut.error(404, "Usuario con ID " + id + " no encontrado", null);
            }
        } catch (Exception e) {
            return outPut.error(500, "Error de sistema", null);
        }
    }

    public OutputEntity<UserEntity> deleteUser(Long id) {
        OutputEntity<UserEntity> outPut = new OutputEntity<>();
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
            if (optionalUserEntity.isPresent()) {
                userRepository.deleteById(id);
                return outPut.error(200, "Usuario eliminado", null);
            } else {
                return outPut.error(404, "Usuario con ID " + id + " no encontrado", null);
            }
        } catch (Exception e) {
            return outPut.error(500, "Error de sistema", null);
        }
    }

}
