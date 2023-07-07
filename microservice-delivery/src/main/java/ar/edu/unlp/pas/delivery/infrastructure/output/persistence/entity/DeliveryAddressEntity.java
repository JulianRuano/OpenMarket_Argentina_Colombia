package ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "deliveryAddress")
@Data
public class DeliveryAddressEntity {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeliveryAddress;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "houseNumber")
    private Integer houseNumber;

    @Column(name = "floorNumber")
    private Integer floorNumber;

    @Column(name = "department")
    private Integer department;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "province")
    private String province;

    @NotNull
    @Column(name = "country")
    private String country;
}
