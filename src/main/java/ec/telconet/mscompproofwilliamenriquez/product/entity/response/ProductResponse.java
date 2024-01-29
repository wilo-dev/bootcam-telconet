package ec.telconet.mscompproofwilliamenriquez.product.entity.response;

import ec.telconet.mscompproofwilliamenriquez.product.entity.model.ProductEntity;
import lombok.Data;

@Data
public class ProductResponse {
    private Long identificador;
    private String productoNombre;
    private Integer cantidad;
    private String precio;
    private String imagenProducto;
    private Boolean estado;

    public ProductResponse(ProductEntity p) {
        this.identificador = p.getId();
        this.productoNombre = p.getProductoNombre();
        this.cantidad = p.getCantidad();
        this.precio = p.getPrecio();
        this.imagenProducto = p.getImagenProducto();
        this.estado = p.getEstado();
    }
}
