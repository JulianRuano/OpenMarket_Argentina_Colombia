package ar.edu.unlp.pas.person.dtos.address;

import ar.edu.unlp.pas.person.models.Address;

public class AddressGetDto {
    private Long id;
    private String street;
    private Integer houseNumber;
    private Integer floorNumber;
    private String department;
    private String city;
    private String province;
    private String country;
    private Long personId;

    public AddressGetDto() {
    }

    public AddressGetDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.floorNumber = address.getFloorNumber();
        this.department = address.getDepartment();
        this.city = address.getCity();
        this.province = address.getProvince();
        this.country = address.getCountry();
        this.personId = address.getPerson().getId();
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
