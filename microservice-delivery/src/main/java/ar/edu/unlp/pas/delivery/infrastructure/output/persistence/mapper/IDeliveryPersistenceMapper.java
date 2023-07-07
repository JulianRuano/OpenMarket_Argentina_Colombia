package ar.edu.unlp.pas.delivery.infrastructure.output.persistence.mapper;

import ar.edu.unlp.pas.delivery.domain.model.Delivery;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.entity.DeliveryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDeliveryPersistenceMapper {
    DeliveryEntity toDeliveryEntity(Delivery delivery);
    Delivery toDelivery(DeliveryEntity deliveryEntity);
}
