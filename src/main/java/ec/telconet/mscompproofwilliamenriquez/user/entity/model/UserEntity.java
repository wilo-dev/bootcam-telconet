package ec.telconet.mscompproofwilliamenriquez.user.entity.model;

import ec.telconet.mscompproofwilliamenriquez.util.entity.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity extends AuditableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String nombre;

    @Column(name = "lastname", nullable = false, length = 50)
    private String apellido;

    @Column(name = "username", nullable = false, length = 50)
    private String usuario;

    @Column(name = "password", nullable = false, length = 50)
    private String clave;

    @Column(name = "email", nullable = false, length = 50)
    private String correo;

    @Column(name = "status", nullable = false, length = 1)
    private Character estado;

    @Column(name = "admin", nullable = false)
    private Boolean administrator;


}
