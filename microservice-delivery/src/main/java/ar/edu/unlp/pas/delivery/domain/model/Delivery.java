package ar.edu.unlp.pas.delivery.domain.model;

import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private Long idDelivery;
    private Long idPublication;
    private int quantity;
    private Long idCheckout;
    private Long idPerson;
    private Date dateDelivery;
    private DeliveryStatus deliveryStatus;
    private DeliveryAddress address;
}
