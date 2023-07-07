package ar.edu.unlp.pas.person.models;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity()
public class Address {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "houseNumber")
    private Integer houseNumber;

    @Column(name = "floorNumber")
    private Integer floorNumber;

    @Column(name = "department")
    private String department;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "province")
    private String province;

    @NotNull
    @Column(name = "country")
    private String country;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public Address(Long id, String street, Integer houseNumber, Integer floorNumber, String department, String city, String province, String country, Person person) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
        this.person = person;
    }

    public Address() {
    }

    public Address(AddressCreateDto addressDTO) {
        this.street = addressDTO.getStreet();
        this.houseNumber = addressDTO.getHouseNumber();
        this.floorNumber = addressDTO.getFloorNumber();
        this.department = addressDTO.getDepartment();
        this.city = addressDTO.getCity();
        this.province = addressDTO.getProvince();
        this.country = addressDTO.getCountry();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

