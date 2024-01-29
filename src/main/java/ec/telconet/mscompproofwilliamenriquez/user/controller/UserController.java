package ec.telconet.mscompproofwilliamenriquez.user.controller;

import ec.telconet.mscompproofwilliamenriquez.user.entity.request.UserRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.user.service.UserService;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<OutputEntity<List<UserResponse>>> getAllEnabledUsers() {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = userService.getAllEnabledUsers();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<UserResponse>>> getAllUsers() {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = userService.getAllUsers();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputEntity<UserResponse>> getUser(@PathVariable Long id) {
        OutputEntity<UserResponse> out = null;
        try {
            out = userService.getUser(id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<UserResponse>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PostMapping
    public ResponseEntity<OutputEntity<String>> createUser(@RequestBody UserRequest data) {
        OutputEntity<String> out = null;
        try {
            out = userService.createUser(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> deleteUser(@PathVariable Long id) {
        OutputEntity<String> out = null;
        try {
            out = userService.deleteUser(id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> updateUser(@RequestBody UserRequest data, @PathVariable Long id) {
        OutputEntity<String> out = null;
        try {
            out = userService.updateUser(data, id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<OutputEntity<List<UserResponse>>> findByName(@PathVariable String name) {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = userService.findByName(name);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/usuario/{user}")
    public ResponseEntity<OutputEntity<List<UserResponse>>> findByUser(@PathVariable String user) {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = userService.findByUser(user);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<OutputEntity<List<UserResponse>>> findByMail(@PathVariable String email) {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = userService.findByMail(email);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/page")
    public ResponseEntity<OutputEntity<List<UserResponse>>> getLastUser(@RequestParam Integer paginaInicial,
                                                                        @RequestParam Integer paginaFinal) {
        OutputEntity<List<UserResponse>> out = null;
        try {
            out = this.userService.getLastUser(paginaInicial, paginaFinal);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<UserResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
