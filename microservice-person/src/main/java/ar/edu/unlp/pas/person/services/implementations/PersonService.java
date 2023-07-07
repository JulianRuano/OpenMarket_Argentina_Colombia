package ar.edu.unlp.pas.person.services.implementations;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;
import ar.edu.unlp.pas.person.dtos.person.PersonCreateDto;
import ar.edu.unlp.pas.person.enums.UserRole;
import ar.edu.unlp.pas.person.models.Address;
import ar.edu.unlp.pas.person.models.Person;
import ar.edu.unlp.pas.person.repositories.JpaPersonRepository;
import ar.edu.unlp.pas.person.services.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PersonService implements IPersonService {

    @Autowired
    private JpaPersonRepository personRepository;

    @Override
    public Person createPerson(PersonCreateDto personDto) throws Exception {
        int age = this.calculateAge(personDto.getBirth());
        if (age < 18 || age > 125) {
            throw new Exception("Edad menor a 18 o mayor a 125");
        }

        if (personRepository.existsByEmail(personDto.getEmail())) {
            throw new Exception("El correo ingresado ya existe en el sistema");
        }

        if (
                !Objects.equals(personDto.getRole(), UserRole.ADMIN.getRole()) &&
                        !Objects.equals(personDto.getRole(), UserRole.USER.getRole()) &&
                        !Objects.equals(personDto.getRole(), UserRole.SELLER.getRole()) &&
                        !Objects.equals(personDto.getRole(), UserRole.DELIVERER.getRole())
        ) {
            throw new Exception("El rol ingresado no es valido");
        }

        Person person = new Person(personDto);
        Address billingAddress = new Address(personDto.getBillingAddress());
        billingAddress.setPerson(person);
        HashSet<Address> shippingAddresses = new HashSet<>();

        for (AddressCreateDto addressDTO : personDto.getShippingAddress()) {
            Address shippingAddress = new Address(addressDTO);
            shippingAddress.setPerson(person);
            shippingAddresses.add(shippingAddress);
        }
        person.setBillingAddress(billingAddress);
        person.setShippingAddresses(shippingAddresses);

        return personRepository.save(person);
    }

    @Override
    public List<Person> listPersons() {
        return personRepository.findAllWithAddresses();
    }

    @Override
    public Person getPersonByEmail(String email) {
        return personRepository.findByEmailWithAddresses(email);
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsPersonByEmail(String email) {
        return personRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        return personRepository.existsByEmailAndPassword(email, password);
    }

    private int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }
}