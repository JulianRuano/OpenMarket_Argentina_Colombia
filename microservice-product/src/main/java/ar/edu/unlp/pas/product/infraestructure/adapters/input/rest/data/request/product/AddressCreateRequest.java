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
public class AddressCreateRequest {
    @NotEmpty(message = "Street may not be empty")
    private String street;

    @NotEmpty(message = "City may not be empty")
    private String city;

    @NotNull(message = "House number may not be empty")
    @Min(value = 1, message = "House number must be equal or greater than 1")
    private int houseNumber;

    @NotEmpty(message = "Province may not be empty")
    private String province;

    @NotEmpty(message = "Country may not be empty")
    private String country;

}
