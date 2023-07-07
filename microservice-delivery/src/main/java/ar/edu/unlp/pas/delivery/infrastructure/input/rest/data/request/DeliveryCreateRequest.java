package ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.request;
import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import ar.edu.unlp.pas.delivery.domain.model.DeliveryAddress;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity.DeliveryAddressRequest;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCreateRequest {

    @NotNull(message = "idPublication  number may not be empty")
    @Min(value = 1, message = "idPublication number must be equal or greater than 1")
    private Long idPublication;
    @NotNull(message = "idCheckout number may not be empty")
    @Min(value = 1, message = "idCheckout number must be equal or greater than 1")
    private Long idCheckout;
    @NotNull(message = "idPerson number may not be empty")
    @Min(value = 1, message = "idPerson number must be equal or greater than 1")
    private Long idPerson;
    @NotNull(message = "delivery address may not be empty")
    private DeliveryAddressRequest deliveryAddress;
    @NotNull(message = "quantity number may not be empty")
    @Min(value = 1, message = "quantity number  must be equal or greater than 1")
    private Integer quantity;

}
