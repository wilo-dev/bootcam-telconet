package ec.telconet.mscompproofwilliamenriquez.product.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productoNombre;
    private Integer cantidad;
    private String precio;
    private String imagenProducto;
    private Boolean estado;
}
