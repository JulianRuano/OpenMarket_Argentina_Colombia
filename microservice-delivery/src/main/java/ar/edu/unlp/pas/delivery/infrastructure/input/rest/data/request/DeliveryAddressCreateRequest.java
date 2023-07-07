package ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.request;

import lombok.*;

import javax.validation.constraints.NotNull;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressCreateRequest {
    @NotNull
    private String street;

    @NotNull
    private Integer houseNumber;

    private Integer floorNumber;

    private Integer department;

    @NotNull
    private String city;

    @NotNull
    private String province;

    @NotNull
    private String country;
}
