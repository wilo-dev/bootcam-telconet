package ec.telconet.mscompproofwilliamenriquez.leccion.controller;


import ec.telconet.mscompproofwilliamenriquez.leccion.entity.model.ProductoEntity;
import ec.telconet.mscompproofwilliamenriquez.leccion.entity.response.ProductoResponse;
import ec.telconet.mscompproofwilliamenriquez.leccion.service.ApiService;
import ec.telconet.mscompproofwilliamenriquez.user.entity.response.UserResponse;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;


    @GetMapping("/consume-api")
    public ResponseEntity<String> consumeApi() {
        String apiResponse = apiService.fetchDataFromApi();
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/consume-and-save")
    public String consumeApiAndSaveToDatabase(@RequestParam int skip, @RequestParam int limit) {
        apiService.fetchDataAndSaveToDatabase(skip, limit);
        return "Datos de la API guardados en la base de datos";
    }


}
