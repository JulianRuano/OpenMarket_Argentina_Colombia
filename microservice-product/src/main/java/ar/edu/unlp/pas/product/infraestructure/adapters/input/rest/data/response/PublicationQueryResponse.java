package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response;

import ar.edu.unlp.pas.product.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PublicationQueryResponse {
    private Long publicationId;
    private Integer stock;
    private Double sellPrice;
    private Double minPrice;
    private Double maxPrice;
    private Boolean isActive;
    private Product product;
}
