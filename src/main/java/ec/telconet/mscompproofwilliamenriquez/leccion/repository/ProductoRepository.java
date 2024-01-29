package ec.telconet.mscompproofwilliamenriquez.leccion.repository;

import ec.telconet.mscompproofwilliamenriquez.leccion.entity.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
