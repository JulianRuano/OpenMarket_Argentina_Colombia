package ar.edu.unlp.pas.cart.dtos.Person;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;
import java.util.HashSet;
import java.util.Set;

public class PersonGetDto {
    private Long id;
    private String email;
    private String fullName;
    private Integer age;
    private String role;
    private Set<AddressGetDto> shippingAddresses = new HashSet<>();
    private AddressGetDto billingAddress;

    public PersonGetDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AddressGetDto> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Set<AddressGetDto> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public AddressGetDto getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressGetDto billingAddress) {
        this.billingAddress = billingAddress;
    }
}
