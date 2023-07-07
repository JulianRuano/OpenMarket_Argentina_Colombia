package ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DeliveryAddressQueryResponse {
    private Long deliveryAddressId;
    private String street;
    private Integer houseNumber;
    private String city;
    private String province;
    private String country;
}
