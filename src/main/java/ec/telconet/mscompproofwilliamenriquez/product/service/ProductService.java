package ec.telconet.mscompproofwilliamenriquez.product.service;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.ProductEntity;
import ec.telconet.mscompproofwilliamenriquez.product.entity.request.ProductRequest;
import ec.telconet.mscompproofwilliamenriquez.product.entity.response.ProductResponse;
import ec.telconet.mscompproofwilliamenriquez.product.repository.ProductRepository;
import ec.telconet.mscompproofwilliamenriquez.util.entity.OutputEntity;
import ec.telconet.mscompproofwilliamenriquez.util.enums.MessageEnum;
import ec.telconet.mscompproofwilliamenriquez.util.exception.MyException;
import ec.telconet.mscompproofwilliamenriquez.util.helper.MethodHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public OutputEntity<List<ProductResponse>> getAllEnableProduct() {
        OutputEntity<List<ProductResponse>> outPut = new OutputEntity<>();
        try {
            List<ProductEntity> productEntities = productRepository.findByEstadoTrue();
            if (productEntities.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<ProductResponse> productResponses = productEntities.stream().map(ProductResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), productResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<ProductResponse>> getAllProducts() {
        OutputEntity<List<ProductResponse>> outPut = new OutputEntity<>();
        try {
            List<ProductEntity> productEntities = productRepository.findAll();
            if (productEntities.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<ProductResponse> productResponses = productEntities.stream().map(ProductResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), productResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<ProductResponse> getProduct(Long id) {
        OutputEntity<ProductResponse> outPut = new OutputEntity<>();
        try {
            Optional<ProductEntity> productEntity = productRepository.findByIdAndEstado(id, true);
            if (productEntity.isPresent()) {
                ProductResponse productResponse = new ProductResponse(productEntity.get());
                return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), productResponse);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> createProduct(ProductRequest data) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            // TODO: nombre el producto en mayuscula
            MethodHelper.isUpperCase(data.getProductoNombre());

            ProductEntity productEntity = new ProductEntity(data);
            // TODO: guardamos
            productRepository.save(productEntity);
            return outPut.ok(MessageEnum.CREATE.getCode(), MessageEnum.CREATE.getMensaje(), null);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> deleteProduct(Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<ProductEntity> existeProduct = productRepository.findById(id);
            if (existeProduct.isPresent()) {
                ProductEntity productStatus = existeProduct.get();
                productStatus.setEstado(false);
                productRepository.save(productStatus);
                return outPut.ok(MessageEnum.DELETE.getCode(), MessageEnum.DELETE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<String> updateProduct(ProductRequest data, Long id) {
        OutputEntity<String> outPut = new OutputEntity<>();
        try {
            Optional<ProductEntity> existeProduct = productRepository.findById(id);

            if (existeProduct.isPresent()) {
                // TODO: nombre sea en mayuscula
                MethodHelper.isUpperCase(data.getProductoNombre());

                // TODO: actualizando productEntity
                ProductEntity productEntity = existeProduct.get();
                productEntity.actualizarDatos(data);
                productRepository.save(productEntity);
                return outPut.ok(MessageEnum.UPDATE.getCode(), MessageEnum.UPDATE.getMensaje(), null);
            } else {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (Exception e) {
            return outPut.error();
        }
    }

    public OutputEntity<List<ProductResponse>> findByName(String name) {
        OutputEntity<List<ProductResponse>> outPut = new OutputEntity<>();
        try {
            List<ProductEntity> productEntities = productRepository.findByProduct_name(name);
            if (productEntities.isEmpty()) {
                throw new MyException(MessageEnum.NOT_FOUND.getCode(), MessageEnum.NOT_FOUND.getMensaje());
            }
            List<ProductResponse> productResponses = productEntities.stream().map(ProductResponse::new).collect(Collectors.toList());
            return outPut.ok(MessageEnum.OK.getCode(), MessageEnum.OK.getMensaje(), productResponses);
        } catch (MyException e) {
            return outPut.error(e.getCode(), e.getMessages(), null);
        } catch (DataAccessException e) {
            return outPut.error();
        }
    }
}
