package ec.telconet.mscompproofwilliamenriquez.product.entity.model;

import ec.telconet.mscompproofwilliamenriquez.util.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity extends AuditableEntity {

    @Column(name = "product_name")
    private String productName;

    private Integer amount;

    private String price;

    private String image;

    private Boolean status = true;
}
