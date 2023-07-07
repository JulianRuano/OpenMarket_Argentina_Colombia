package ar.edu.unlp.pas.cart.dtos.ShoppingCart;

import java.util.List;

import ar.edu.unlp.pas.cart.dtos.Publication.PublicationGetDto;

public class ShoppingCartDto {

    private Long id;

    private List<PublicationGetDto> publications;

    public ShoppingCartDto(Long id, List<PublicationGetDto> publications) {
        this.id = id;
        this.publications = publications;
    }

    public ShoppingCartDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PublicationGetDto> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationGetDto> publications) {
        this.publications = publications;
    }

}