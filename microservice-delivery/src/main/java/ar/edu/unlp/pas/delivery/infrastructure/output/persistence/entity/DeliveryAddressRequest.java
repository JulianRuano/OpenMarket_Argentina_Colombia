package ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressRequest {
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
