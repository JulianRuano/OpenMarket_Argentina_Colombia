package ar.edu.unlp.pas.person.models;

import ar.edu.unlp.pas.person.dtos.person.PersonCreateDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Person {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "role")
    private Integer role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    private Set<Address> shippingAddresses;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    public Person() {
    }

    public Person(PersonCreateDto p) {
        this.setEmail(p.getEmail());
        this.setBirth(p.getBirth());
        this.setFirstName(p.getFirstName());
        this.setLastName(p.getLastName());
        this.setRole(p.getRole());
        this.setPassword(p.getPassword());
    }

    public Person(Set<Address> shippingAddresses, Address billingAddress) {
        this.shippingAddresses = shippingAddresses;
        this.billingAddress = billingAddress;
    }

    public Set<Address> getShippingAddresses() {
        return this.shippingAddresses;
    }

    public void setShippingAddresses(Set<Address> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
