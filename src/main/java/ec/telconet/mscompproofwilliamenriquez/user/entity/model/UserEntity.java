package ec.telconet.mscompproofwilliamenriquez.user.entity.model;

import ec.telconet.mscompproofwilliamenriquez.user.entity.request.userRequest;
import ec.telconet.mscompproofwilliamenriquez.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String nombre;

    @Column(name = "lastname", nullable = false, length = 50)
    private String apellido;

    @Column(name = "username", nullable = false, length = 50)
    private String usuario;

    @Column(name = "password", nullable = false)
    private String clave;

    @Column(name = "email", nullable = false, length = 50)
    private String correo;

    @Column(name = "status", nullable = false, length = 1)
    private Character estado;

    @Column(name = "admin", nullable = false)
    private Boolean administrator;


    public UserEntity(userRequest data) {
        this.nombre = data.getNombreUsuario();
        this.apellido = data.getApellidoUsuario();
        this.usuario = data.getUsuario();
        this.clave = data.getClave();
        this.correo = data.getCorreo();
        this.estado = 'A';
        this.administrator = false;
    }

    public void actualizarDatos(userRequest data) {
        this.nombre = data.getNombreUsuario();
        this.apellido = data.getApellidoUsuario();
        this.usuario = data.getUsuario();
        this.clave = data.getClave();
        this.correo = data.getCorreo();
        this.setUpdatedAt(new Date());
    }

}
