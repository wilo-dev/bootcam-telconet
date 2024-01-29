package ec.telconet.mscompproofwilliamenriquez.leccion.entity.response;

import ec.telconet.mscompproofwilliamenriquez.leccion.entity.model.ProductoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {
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
    private List<String> images;

    public ProductoResponse(ProductoEntity p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.description = p.getDescription();
        this.price = p.getPrice();
        this.discountPercentage = p.getDiscountPercentage();
        this.rating = p.getRating();
        this.stock = p.getStock();
        this.brand = p.getBrand();
        this.category = p.getCategory();
        this.thumbnail = p.getThumbnail();
        this.images = p.getImages();
    }
}
