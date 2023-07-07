package ar.edu.unlp.pas.cart.dtos.ShoppingCart;

import javax.validation.constraints.NotNull;

public class AddProductDto {
    @NotNull(message = "PublicationId number may not be empty")
    public String publicationId;
    @NotNull(message = "quantity number may not be empty")
    public int quantity;

    public AddProductDto() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AddProductDto(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }
}
