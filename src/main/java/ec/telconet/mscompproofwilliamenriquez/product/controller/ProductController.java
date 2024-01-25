package ec.telconet.mscompproofwilliamenriquez.product.controller;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.ProductEntity;
import ec.telconet.mscompproofwilliamenriquez.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<ProductEntity> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product")
    public List<ProductEntity> getAllEnabledProducts() {
        return service.getAllEnableProducts();
    }


    @GetMapping("/product/{id}")
    public ProductEntity getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return service.saveProduct(product);
    }

    @PutMapping("/product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity updatedProduct(@RequestBody ProductEntity product, @PathVariable Long id) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        service.disableClient(id);
    }

//    @DeleteMapping("/product/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteProduct(@PathVariable Long id) {
//        service.deleteProduct(id);
//    }
}
