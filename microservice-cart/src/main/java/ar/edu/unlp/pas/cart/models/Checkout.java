package ar.edu.unlp.pas.cart.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "checkout")
public class Checkout {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date dateCheckout;

    @Column(name = "personId")
    private Long personId;

    @Column(name = "shippingAddressId")
    private Long shippingAddressId;
    @Column(name = "billingAddressId")
    private Long billingAddressId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "checkout_id")
    private Set<CartItem> cartItems = new HashSet<>();

    public Checkout() {
    }

    public Checkout(Date dateCheckout, Long personId, Long shippingAddressId, Long billingAddressId, Set<CartItem> cartItems) {
        this.dateCheckout = dateCheckout;
        this.personId = personId;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
        this.cartItems.addAll(cartItems);
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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
