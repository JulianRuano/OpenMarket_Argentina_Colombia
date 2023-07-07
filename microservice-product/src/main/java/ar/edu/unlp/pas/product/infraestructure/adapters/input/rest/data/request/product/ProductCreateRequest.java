package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product.AddressCreateRequest;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    @Min(value = 0, message = "Price must be equal or greater than 0")
    private double price;

    @NotEmpty(message = "Description may not be empty")
    private String description;

    @NotEmpty(message = "Category may not be empty")
    private String category;
    @NotNull(message = "PersonId may not be empty")
    @Min(value = 1, message = "personId  must be equal or greater than 1")
    private Long personId;
    @NotNull(message = "Address may not be null")
    private AddressCreateRequest address;

}