package ar.edu.unlp.pas.person.services.interfaces;

import ar.edu.unlp.pas.person.dtos.person.PersonCreateDto;
import ar.edu.unlp.pas.person.models.Person;

import java.util.List;

public interface IPersonService {
    Person createPerson(PersonCreateDto personDto) throws Exception;

    List<Person> listPersons();

    Person getPersonByEmail(String email);

    boolean existsPersonByEmail(String email);

    boolean authenticateUser(String email, String password);

    Person getPersonById(Long id);
}