package ar.edu.unlp.pas.product.domain.models;
import javax.persistence.*;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long id;
    private String street;
    private String city;
    private Integer houseNumber;
    private String province;
    private String country;

}

