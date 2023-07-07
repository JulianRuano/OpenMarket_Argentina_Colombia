package ar.edu.unlp.pas.person.services.interfaces;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;
import ar.edu.unlp.pas.person.models.Address;

import java.util.List;

public interface IAddressService {

    Address createAddress(AddressCreateDto address) throws Exception;

    List<Address> listAddresses();

    Address getAddressById(long id);

    List<Address> getAddressByPersonId(long personId);

    Address getBillingAddressByPersonId(long id);

    Address getShippingAddressByPersonId(long id);
}
