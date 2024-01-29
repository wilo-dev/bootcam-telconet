package ec.telconet.mscompproofwilliamenriquez.product.repository;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.ProductEntity;
import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByEstadoTrue();

    Optional<ProductEntity> findByIdAndEstado(Long id, Boolean estado);

    //SELECT * FROM BOOTCAM.products WHERE product_name LIKE '%' "ca" '%'
    @Query(value = "SELECT * FROM BOOTCAM.products WHERE product_name LIKE %:name%", nativeQuery = true)
    List<ProductEntity> findByProduct_name(String name);
}
