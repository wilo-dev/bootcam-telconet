package ec.telconet.mscompproofwilliamenriquez.product.entity.model;

import ec.telconet.mscompproofwilliamenriquez.product.entity.request.ProductRequest;
import ec.telconet.mscompproofwilliamenriquez.user.entity.request.UserRequest;
import ec.telconet.mscompproofwilliamenriquez.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity extends AuditableEntity {

    @Column(name = "product_name", nullable = false, length = 50)
    private String productoNombre;

    @Column(name = "amount", nullable = false, length = 50)
    private Integer cantidad;

    @Column(name = "price", nullable = false, length = 50)
    private String precio;

    @Column(name = "image_product", nullable = false)
    private String imagenProducto;

    @Column(name = "status", nullable = false)
    private Boolean estado;

    public ProductEntity(ProductRequest data) {
        this.productoNombre = data.getProductoNombre();
        this.cantidad = data.getCantidad();
        this.precio = data.getPrecio();
        this.imagenProducto = data.getImagenProducto();
        this.estado = true;
    }

    public void actualizarDatos(ProductRequest data) {
        this.productoNombre = data.getProductoNombre();
        this.cantidad = data.getCantidad();
        this.precio = data.getPrecio();
        this.imagenProducto = data.getImagenProducto();
        this.setUpdatedAt(new Date());
    }
}
