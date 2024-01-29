package ec.telconet.mscompproofwilliamenriquez.product.controller;

import ec.telconet.mscompproofwilliamenriquez.product.entity.request.ProductRequest;
import ec.telconet.mscompproofwilliamenriquez.product.entity.response.ProductResponse;
import ec.telconet.mscompproofwilliamenriquez.product.service.ProductService;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<OutputEntity<List<ProductResponse>>> getAllEnableProduct() {
        OutputEntity<List<ProductResponse>> out = null;
        try {
            out = productService.getAllEnableProduct();
            return new ResponseEntity<>(out, out.getCode());
        } catch (DataAccessException e) {
            out = new OutputEntity<List<ProductResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<ProductResponse>>> getAllProducts() {
        OutputEntity<List<ProductResponse>> out = null;
        try {
            out = productService.getAllProducts();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<ProductResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputEntity<ProductResponse>> getProduct(@PathVariable Long id) {
        OutputEntity<ProductResponse> out = null;
        try {
            out = productService.getProduct(id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<ProductResponse>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PostMapping
    public ResponseEntity<OutputEntity<String>> createProduct(@RequestBody ProductRequest data) {
        OutputEntity<String> out = null;
        try {
            out = productService.createProduct(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> deleteProduct(@PathVariable Long id) {
        OutputEntity<String> out = null;
        try {
            out = productService.deleteProduct(id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> updateProduct(@RequestBody ProductRequest data, @PathVariable Long id) {
        OutputEntity<String> out = null;
        try {
            out = productService.updateProduct(data, id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/nane/{name}")
    public ResponseEntity<OutputEntity<List<ProductResponse>>> findByName(@PathVariable String name) {
        OutputEntity<List<ProductResponse>> out = null;
        try {
            out = productService.findByName(name);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<ProductResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


}
