package ar.edu.unlp.pas.cart.services.interfaces;

import java.util.List;
import java.util.Set;

import ar.edu.unlp.pas.cart.dtos.Publication.PublicationGetDto;

public interface IProductService {

    List<PublicationGetDto> getPublicationByIds(Set<Long> ids);
}
