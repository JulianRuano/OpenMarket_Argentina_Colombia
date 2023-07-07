package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.publication;

import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.ProductCreateRequest;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationCreateRequest {
    @Min(value = 0, message = "stock must be equal or greater than 0")
    private Integer stock;
    @Min(value = 0, message = "sellPrice must be equal or greater than 0")
    private Double sellPrice;

    @Min(value = 0, message = "manPrice must be equal or greater than 0")
    private Double minPrice;
    @Min(value = 0, message = "maxPrice must be equal or greater than 0")
    private Double maxPrice;

    @NotNull(message = "isActive may not be null")
    private Boolean isActive;
    
    @NotNull(message = "IdProduct may not be null")
    private Long productId;


}
