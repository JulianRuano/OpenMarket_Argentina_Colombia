package ar.edu.unlp.pas.cart.services.interfaces;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;
import ar.edu.unlp.pas.cart.models.Checkout;

public interface IDeliveryService {
    boolean createDeliveries(Checkout checkout, AddressGetDto deliveryAddressDto);
}
