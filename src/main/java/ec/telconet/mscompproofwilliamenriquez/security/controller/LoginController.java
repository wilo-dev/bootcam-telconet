package ec.telconet.mscompproofwilliamenriquez.security.controller;

import ec.telconet.mscompproofwilliamenriquez.security.entity.request.LoginRequest;
import ec.telconet.mscompproofwilliamenriquez.security.entity.response.LoginResponse;
import ec.telconet.mscompproofwilliamenriquez.security.service.LoginService;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<OutputEntity<LoginResponse>> login(@RequestBody LoginRequest data) {
        OutputEntity<LoginResponse> out = null;
        try {
            out = this.loginService.login(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<LoginResponse>().error();
            return new ResponseEntity<>(out, out.getCode());
        }

    }
}
