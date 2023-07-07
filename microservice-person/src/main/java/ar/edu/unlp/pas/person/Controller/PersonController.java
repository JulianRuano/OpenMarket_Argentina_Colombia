package ar.edu.unlp.pas.person.Controller;

import ar.edu.unlp.pas.person.dtos.person.PersonCreateDto;
import ar.edu.unlp.pas.person.dtos.person.PersonGetDto;
import ar.edu.unlp.pas.person.models.Person;
import ar.edu.unlp.pas.person.services.implementations.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping()
    public ResponseEntity<PersonGetDto> createPerson(@Valid @RequestBody PersonCreateDto personDto) {
        try {
            Person person = personService.createPerson(personDto);
            PersonGetDto personGetDTO = new PersonGetDto(person);
            return ResponseEntity.ok(personGetDTO);
        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<PersonGetDto>> listPersons() {
        List<Person> persons = personService.listPersons();
        List<PersonGetDto> personsGetDTO = new ArrayList<>();

        for (Person person : persons) {
            PersonGetDto personGetDTO = new PersonGetDto(person);
            personsGetDTO.add(personGetDTO);
        }
        return ResponseEntity.ok(personsGetDTO);
    }

    @GetMapping(params = "email")
    public ResponseEntity<PersonGetDto> getPersonByEmail(@RequestParam(value = "email") String email) {
        Person person = personService.getPersonByEmail(email);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        PersonGetDto personGetDTO = new PersonGetDto(person);
        try {
            return ResponseEntity.ok(personGetDTO);
        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonGetDto> getPersonById(@PathVariable long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        PersonGetDto personGetDTO = new PersonGetDto(person);
        return ResponseEntity.ok(personGetDTO);
    }
}
