package ar.edu.unlp.pas.delivery.infrastructure.input.rest.mapper;

import ar.edu.unlp.pas.delivery.domain.model.Delivery;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.request.DeliveryCreateRequest;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryCreateResponse;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryQueryResponse;

public interface IDeliveryRestMapper {
    Delivery toDelivery(DeliveryCreateRequest deliveryCreateRequest);
    DeliveryCreateResponse toDeliveryCreateResponse(Delivery delivery);
    DeliveryQueryResponse toDeliveryQueryResponse(Delivery delivery);
}
