package ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity;

import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
@Data
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long idDelivery;

    @Column(name = "id_publication", nullable = false)
    private Long idPublication;

    @Column(name = "id_checkout", nullable = false)
    private Long idCheckout;

    @Column(name = "id_person", nullable = false)
    private Long idPerson;
    @Column(name = "quantity",nullable = false)
    private Integer quantity;
    @Column(name = "date_delivery", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDelivery = new Date();

    @Column(name = "delivery_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.STATUS_PENDING;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private DeliveryAddressEntity address;
}
