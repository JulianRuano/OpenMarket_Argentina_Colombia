package ar.edu.unlp.pas.product.infraestructure.adapters.input.rest.data.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressQueryResponse {
    private Long addressId;
    private String street;
    private Integer houseNumber;
    private String city;
    private String province;
    private String country;
}