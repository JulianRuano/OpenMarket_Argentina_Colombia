package ar.edu.unlp.pas.cart.services.interfaces;

import ar.edu.unlp.pas.cart.dtos.Person.PersonGetDto;

public interface IPersonService {
    PersonGetDto getPersonDtoById(long personId);
}
