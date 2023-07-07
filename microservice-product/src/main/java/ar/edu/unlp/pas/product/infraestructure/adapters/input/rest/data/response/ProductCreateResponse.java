package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response;
import ar.edu.unlp.pas.product.domain.models.Address;
import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.entity.AddressEntity;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCreateResponse {
    private Long productId;
    private String name;
    private  Double price;
    private  String description;
    private String category;
    private AddressQueryResponse address;
    private Long personId;

}
