package ar.edu.unlp.pas.person.dtos.person;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class PersonCreateDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birth;

    @NotNull
    @Email(message = "Debe ser un formato de mail valido")
    private String email;

    @NotNull
    private Integer role;

    @NotNull
    private String password;

    @NotNull
    private AddressCreateDto billingAddress;

    @NotEmpty
    private Set<AddressCreateDto> shippingAddress;

    public PersonCreateDto() {
    }

    public PersonCreateDto(String firstName, String lastName, LocalDate birth, String email, Integer role,
                           String password, AddressCreateDto billingAddress, Set<AddressCreateDto> shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.email = email;
        this.role = role;
        this.password = password;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public AddressCreateDto getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressCreateDto billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Set<AddressCreateDto> getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Set<AddressCreateDto> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.getBirth(), currentDate).getYears();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

