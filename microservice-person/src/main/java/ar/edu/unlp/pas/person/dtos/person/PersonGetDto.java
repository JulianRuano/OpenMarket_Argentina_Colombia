package ar.edu.unlp.pas.person.dtos.person;

import ar.edu.unlp.pas.person.dtos.address.AddressGetDto;
import ar.edu.unlp.pas.person.enums.UserRole;
import ar.edu.unlp.pas.person.models.Address;
import ar.edu.unlp.pas.person.models.Person;

import java.time.LocalDate;
import java.time.Period;
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

    public PersonGetDto(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.fullName = person.getFirstName() + " " + person.getLastName();
        switch (person.getRole()) {
            case 1:
                this.role = UserRole.ADMIN.name();
                break;
            case 2:
                this.role = UserRole.USER.name();
                break;
            case 3:
                this.role = UserRole.SELLER.name();
                break;
            case 4:
                this.role = UserRole.DELIVERER.name();
                break;
        }
        if (person.getBirth() != null) {
            LocalDate birthLocalDate = person.getBirth();
            this.age = calculateAge(birthLocalDate);
        }
        for (Address address : person.getShippingAddresses()) {
            AddressGetDto addressGetDto = new AddressGetDto(address);
            this.shippingAddresses.add(addressGetDto);
        }
        this.billingAddress = new AddressGetDto(person.getBillingAddress());
    }

    public static int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}