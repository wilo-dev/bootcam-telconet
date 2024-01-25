package ec.telconet.mscompproofwilliamenriquez.product.controller;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<UserEntity> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody UserEntity user) {
        return service.saveUser(user);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity updatedUser(@RequestBody UserEntity user, @PathVariable Long id) {
        UserEntity getUserCurrent = service.getUser(id);
        getUserCurrent.setUsername(user.getUsername());
        getUserCurrent.setPassword(user.getPassword());
        getUserCurrent.setEmail(user.getEmail());
        getUserCurrent.setStatus(user.getStatus());
        getUserCurrent.setUpdatedAt(new Date());
        return service.saveUser(getUserCurrent);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }


}
