package ec.telconet.mscompproofwilliamenriquez.user.repository;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
