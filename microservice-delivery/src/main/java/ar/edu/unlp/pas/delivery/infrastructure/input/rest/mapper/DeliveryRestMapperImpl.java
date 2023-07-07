package ar.edu.unlp.pas.delivery.infrastructure.input.rest.mapper;

import ar.edu.unlp.pas.delivery.domain.enums.DeliveryStatus;
import ar.edu.unlp.pas.delivery.domain.model.Delivery;
import ar.edu.unlp.pas.delivery.domain.model.DeliveryAddress;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.request.DeliveryCreateRequest;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryAddressQueryResponse;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryCreateResponse;
import ar.edu.unlp.pas.delivery.infrastructure.input.rest.data.response.DeliveryQueryResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeliveryRestMapperImpl implements IDeliveryRestMapper{
    @Override
    public Delivery toDelivery(DeliveryCreateRequest deliveryCreateRequest) {
        if(deliveryCreateRequest==null){
            return null;
        }
        Delivery  delivery =new Delivery();
        delivery.setIdPublication(deliveryCreateRequest.getIdPublication());
        delivery.setIdCheckout(deliveryCreateRequest.getIdCheckout());
        delivery.setIdPerson(deliveryCreateRequest.getIdPerson());
        delivery.setDateDelivery(new Date());
        DeliveryAddress deliveryAddress=new DeliveryAddress();
        deliveryAddress.setStreet(deliveryCreateRequest.getDeliveryAddress().getStreet());
        deliveryAddress.setHouseNumber(deliveryCreateRequest.getDeliveryAddress().getHouseNumber());
        deliveryAddress.setCity(deliveryCreateRequest.getDeliveryAddress().getCity());
        deliveryAddress.setProvince(deliveryCreateRequest.getDeliveryAddress().getProvince());
        deliveryAddress.setCountry(deliveryCreateRequest.getDeliveryAddress().getCountry());
        delivery.setAddress(deliveryAddress);
        delivery.setQuantity(deliveryCreateRequest.getQuantity());
        delivery.setDeliveryStatus(DeliveryStatus.STATUS_PENDING);
        return delivery;
    }

    @Override
    public DeliveryCreateResponse toDeliveryCreateResponse(Delivery delivery) {
       if(delivery == null){
           return null;
       }
       DeliveryCreateResponse deliveryCreateResponse=new DeliveryCreateResponse();
       deliveryCreateResponse.setIdDelivery(delivery.getIdDelivery());
       deliveryCreateResponse.setIdPublication(delivery.getIdPublication());
       deliveryCreateResponse.setIdCheckout(delivery.getIdCheckout());
       deliveryCreateResponse.setQuantity(delivery.getQuantity());
       deliveryCreateResponse.setIdPerson(delivery.getIdPerson());
       deliveryCreateResponse.setDateDelivery(delivery.getDateDelivery());
       deliveryCreateResponse.setDeliveryStatus(delivery.getDeliveryStatus());
        DeliveryAddressQueryResponse deliveryAddressQueryResponse=new DeliveryAddressQueryResponse();
        deliveryAddressQueryResponse.setStreet(delivery.getAddress().getStreet());
        deliveryAddressQueryResponse.setHouseNumber(delivery.getAddress().getHouseNumber());
        deliveryAddressQueryResponse.setCity(delivery.getAddress().getCity());
        deliveryAddressQueryResponse.setProvince(delivery.getAddress().getProvince());
        deliveryAddressQueryResponse.setCountry(delivery.getAddress().getCountry());
        deliveryAddressQueryResponse.setDeliveryAddressId(delivery.getAddress().getIdDeliveryAddress());

       deliveryCreateResponse.setAddress(deliveryAddressQueryResponse);

       return deliveryCreateResponse;
    }

    @Override
    public DeliveryQueryResponse toDeliveryQueryResponse(Delivery delivery) {
        if(delivery==null){
            return  null;
        }
        DeliveryQueryResponse deliveryQueryResponse=new DeliveryQueryResponse();
        deliveryQueryResponse.setIdDelivery(delivery.getIdDelivery());
        deliveryQueryResponse.setIdPublication(delivery.getIdPublication());
        deliveryQueryResponse.setIdCheckout(delivery.getIdCheckout());
        deliveryQueryResponse.setIdPerson(delivery.getIdPerson());
        deliveryQueryResponse.setQuantity(delivery.getQuantity());
        deliveryQueryResponse.setDateDelivery(delivery.getDateDelivery());
        deliveryQueryResponse.setDeliveryStatus(delivery.getDeliveryStatus());
        DeliveryAddressQueryResponse deliveryAddressQueryResponse=new DeliveryAddressQueryResponse();
        deliveryAddressQueryResponse.setStreet(delivery.getAddress().getStreet());
        deliveryAddressQueryResponse.setHouseNumber(delivery.getAddress().getHouseNumber());
        deliveryAddressQueryResponse.setCity(delivery.getAddress().getCity());
        deliveryAddressQueryResponse.setProvince(delivery.getAddress().getProvince());
        deliveryAddressQueryResponse.setCountry(delivery.getAddress().getCountry());
        deliveryAddressQueryResponse.setDeliveryAddressId(delivery.getAddress().getIdDeliveryAddress());

        deliveryQueryResponse.setAddress(deliveryAddressQueryResponse);

        return deliveryQueryResponse;
    }
}
