package ar.edu.unlp.pas.delivery.domain.model;
import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {
    private Long idDeliveryAddress;
    private String street;
    private Integer houseNumber;
    private String city;
    private String province;
    private String country;
}
