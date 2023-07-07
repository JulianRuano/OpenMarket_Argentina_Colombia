package ar.edu.unlp.pas.cart.dtos.Checkout;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;
import ar.edu.unlp.pas.cart.dtos.Person.PersonGetDto;
import ar.edu.unlp.pas.cart.dtos.Publication.PublicationGetDto;

import java.util.Date;
import java.util.List;

public class CheckoutGetDto {

    private Long id;
    private Date dateCheckout;
    private AddressGetDto addressGetDto;
    private PersonGetDto personGetDto;
    private List<PublicationGetDto> publications;

    public CheckoutGetDto(Long id, Date dateCheckout, AddressGetDto addressGetDto, PersonGetDto personGetDto, List<PublicationGetDto> publications) {
        this.id = id;
        this.dateCheckout = dateCheckout;
        this.addressGetDto = addressGetDto;
        this.personGetDto = personGetDto;
        this.publications = publications;
    }

    public List<PublicationGetDto> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationGetDto> publications) {
        this.publications = publications;
    }

    public AddressGetDto getAddressGetDto() {
        return addressGetDto;
    }

    public void setAddressGetDto(AddressGetDto addressGetDto) {
        this.addressGetDto = addressGetDto;
    }

    public PersonGetDto getPersonGetDto() {
        return personGetDto;
    }

    public void setPersonGetDto(PersonGetDto personGetDto) {
        this.personGetDto = personGetDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCheckout() {
        return dateCheckout;
    }

    public void setDateCheckout(Date dateCheckout) {
        this.dateCheckout = dateCheckout;
    }

}
