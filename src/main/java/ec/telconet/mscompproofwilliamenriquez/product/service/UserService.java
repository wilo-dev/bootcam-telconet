package ec.telconet.mscompproofwilliamenriquez.product.service;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    public UserEntity getUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserEntity saveUser(UserEntity user) {
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
