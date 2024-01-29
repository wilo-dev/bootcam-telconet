package ec.telconet.mscompproofwilliamenriquez.user.repository;

import ec.telconet.mscompproofwilliamenriquez.user.entity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByEstado(Character estado);

    Optional<UserEntity> findByIdAndEstado(Long id, Character estado);

    @Query(value = "SELECT * FROM BOOTCAM.users WHERE name LIKE %:name%", nativeQuery = true)
    List<UserEntity> findByName(String name);

    @Query(value = "SELECT * FROM BOOTCAM.users WHERE username LIKE %:user%", nativeQuery = true)
    List<UserEntity> findByUser(String user);

    @Query(value = "SELECT * FROM BOOTCAM.users WHERE username = :username", nativeQuery = true)
    UserEntity findByUserLogin(String username);

    @Query(value = "SELECT * FROM BOOTCAM.users WHERE email = ?1", nativeQuery = true)
    List<UserEntity> findByMail(String user);

    /*@Query(value = "select * from BOOTCAM.usuario where name LIKE %?1%",nativeQuery = true)
    List<UsuarioEntity> findByName(String name);*/
}
