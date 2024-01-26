package ec.telconet.mscompproofwilliamenriquez.user.controller;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.CreateUserRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.UpdateUserRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.user.service.UserService;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<OutputEntity<List<UserResponse>>> getAllUsers() {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = this.userService.getAllUsers();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error(500, "Error", null);
            return new ResponseEntity<>(out, out.getCode());
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<OutputEntity<UserResponse>> getUser(@PathVariable Long id) {
        OutputEntity<UserResponse> out = null;
        out = userService.getUser(id);
        return new ResponseEntity<>(out, out.getCode());
    }

    @PostMapping("/user")
    public UserEntity createUser(@RequestBody CreateUserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<OutputEntity<UserEntity>> updatedUser(@RequestBody UpdateUserRequest user, @PathVariable Long id) {
        OutputEntity<UserEntity> out = null;
        out = userService.updatedUser(id, user);
        return new ResponseEntity<>(out, out.getCode());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<OutputEntity<UserEntity>> deleteUser(@PathVariable Long id) {
        OutputEntity<UserEntity> out = null;
        out = userService.deleteUser(id);
        return new ResponseEntity<>(out, out.getCode());
    }

}
