package ec.telconet.mscompproofwilliamenriquez.product.service;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.ProductEntity;
import ec.telconet.mscompproofwilliamenriquez.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductEntity> getAllProducts() {
        return repository.findAll();
    }

    public List<ProductEntity> getAllEnableProducts() {
        return repository.findByStatusTrue();
    }

    public ProductEntity getProduct(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ProductEntity saveProduct(ProductEntity product) {
        return repository.save(product);
    }

    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        ProductEntity existingProduct = getProduct(id);
        existingProduct.setProduct_name(updatedProduct.getProduct_name());
        existingProduct.setAmount(updatedProduct.getAmount());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setStatus(updatedProduct.getStatus());
        existingProduct.setUpdatedAt(new Date());
        return repository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public void disableClient(Long id) {
        Optional<ProductEntity> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
            product.setStatus(false);
            repository.save(product);
        }
    }

}
