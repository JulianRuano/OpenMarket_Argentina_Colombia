package ar.edu.unlp.pas.cart.models;

import javax.persistence.*;

@Entity(name = "cart_item")
public class CartItem {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "publicationId")
    private Long publicationId;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shoppingCartId")
    private ShoppingCart shoppingCart;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "checkout_id")
    private Checkout checkout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
