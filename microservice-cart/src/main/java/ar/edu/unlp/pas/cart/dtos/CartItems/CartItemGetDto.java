package ar.edu.unlp.pas.cart.dtos.CartItems;

public class CartItemGetDto {

    private Long publicationId;
    private Integer quantity;
    private Long shoppingCartId;

    public CartItemGetDto(Long publicationId, Integer quantity, Long shoppingCartId) {
        this.publicationId = publicationId;
        this.quantity = quantity;
        this.shoppingCartId = shoppingCartId;
    }

    public CartItemGetDto() {
    }

    public Long getPublicationId() {
        return this.publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;

    }

    public Long getShoppingCartId() {
        return this.shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
