package ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response;

import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import ar.edu.unlp.pas.delivery.domain.model.DeliveryAddress;
import lombok.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DeliveryQueryResponse {
    private Long idDelivery;
    private Long idPublication;
    private Long idCheckout;
    private Long idPerson;
    private Integer quantity;
    private Date dateDelivery;
    private DeliveryStatus deliveryStatus;
    private DeliveryAddressQueryResponse address;
}
