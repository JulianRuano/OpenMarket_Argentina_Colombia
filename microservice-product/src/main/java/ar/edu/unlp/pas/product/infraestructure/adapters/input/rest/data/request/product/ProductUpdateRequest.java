package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.request.product;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {


    @NotEmpty(message = "Name may not be empty")
    private String name;

    @Min(value = 0, message = "Price must be equal or greater than 0")
    private double price;

    @NotEmpty(message = "Description may not be empty")
    private String description;

    @NotEmpty(message = "Category may not be empty")
    private String category;
    @NotNull(message = "Address may not be null")
    private AddressCreateRequest address;

}
