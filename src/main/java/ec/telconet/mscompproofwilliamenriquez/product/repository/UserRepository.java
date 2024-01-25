package ec.telconet.mscompproofwilliamenriquez.product.repository;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
