package ar.edu.unlp.pas.cart.services.interfaces;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;

import java.net.MalformedURLException;

public interface IAddressService {
    AddressGetDto getShippingAddressDtoByPersonId(long personId);

    AddressGetDto getBillingAddressDtoByPersonId(long personId);
}
