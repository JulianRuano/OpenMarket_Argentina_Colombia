package ar.edu.unlp.pas.person.services.implementations;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;
import ar.edu.unlp.pas.person.models.Address;
import ar.edu.unlp.pas.person.models.Person;
import ar.edu.unlp.pas.person.repositories.JpaAddressRepository;
import ar.edu.unlp.pas.person.services.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService {

    @Autowired
    private JpaAddressRepository jpaAddressRepository;

    @Autowired
    private PersonService personService;

    @Override
    public Address createAddress(AddressCreateDto addressDTO) throws Exception {
        Address address = new Address(addressDTO);
        Person person = personService.getPersonById(addressDTO.getPersonId());
        if (person == null){
            throw new Exception();
        }
        address.setPerson(person);
        return jpaAddressRepository.save(address);
    }

    @Override
    public List<Address> listAddresses() {
        return jpaAddressRepository.findAll();
    }

    @Override
    public Address getAddressById(long id) {
        return jpaAddressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> getAddressByPersonId(long personId) {
        return jpaAddressRepository.findByPersonId(personId);
    }

    @Override
    public Address getShippingAddressByPersonId(long personId){
        Person person = personService.getPersonById(personId);
        if (person == null){
            return null;
        }
        return person.getShippingAddresses().stream().findFirst().orElse(null);
    }

    @Override
    public Address getBillingAddressByPersonId(long personId){
        Person person = personService.getPersonById(personId);
        if (person == null){
            return null;
        }
        return person.getBillingAddress();
    }
}
