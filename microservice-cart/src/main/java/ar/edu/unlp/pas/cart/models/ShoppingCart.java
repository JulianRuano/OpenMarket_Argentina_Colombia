package ar.edu.unlp.pas.cart.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personId", unique = true)
    private Long personId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shoppingCart")
    private Set<CartItem> cartItems = new HashSet<>();

    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public Long getPersonId() {
        return personId;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
