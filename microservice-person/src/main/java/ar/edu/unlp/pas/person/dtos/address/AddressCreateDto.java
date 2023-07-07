package ar.edu.unlp.pas.person.dtos.address;

import javax.validation.constraints.NotNull;

public class AddressCreateDto {
    @NotNull(message = "La calle es obligatoria")
    private String street;

    @NotNull(message = "El numero casa es obligatorio")
    private Integer houseNumber;

    @NotNull(message = "El numero de piso es obligatorio")
    private Integer floorNumber;

    @NotNull(message = "El departamento es obligatorio")
    private String department;

    @NotNull(message = "La localidad es obligatorio")
    private String city;

    @NotNull(message = "La provincia es obligatorio")
    private String province;

    @NotNull(message = "El numero es obligatorio")
    private String country;

    @NotNull(message = "La persona es obligatoria")
    private Long personId;

    public AddressCreateDto() {
    }

    public AddressCreateDto(String street, Integer houseNumber, Integer floorNumber, String department,
                            String city, String province, String country, Long personId) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
        this.personId = personId;
    }

    public AddressCreateDto(String street, Integer houseNumber, Integer floorNumber, String department,
                            String city, String province, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.department = department;
        this.city = city;
        this.province = province;
        this.country = country;
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
