package ec.telconet.mscompproofwilliamenriquez.leccion.entity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;
    private String title;
    private String description;
    private Long price;
    private Double discountPercentage;
    private Double rating;
    private Long stock;
    private String brand;
    private String category;
    private String thumbnail;

    @ElementCollection
    @CollectionTable(name = "producto_images", joinColumns = @JoinColumn(name = "producto_id"))
    @Column(name = "image")
    private List<String> images = new ArrayList<>();
    

}
