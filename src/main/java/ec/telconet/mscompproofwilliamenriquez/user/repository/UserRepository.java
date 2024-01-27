package ec.telconet.mscompproofwilliamenriquez.user.repository;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByEstado(Character estado);

    Optional<UserEntity> findByIdAndEstado(Long id, Character estado);

}
