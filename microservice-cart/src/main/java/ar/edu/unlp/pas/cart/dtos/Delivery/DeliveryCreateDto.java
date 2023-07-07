package ar.edu.unlp.pas.cart.dtos.Delivery;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;

import java.util.Date;

public class DeliveryCreateDto {
    private AddressGetDto deliveryAddress;
    private Integer quantity;
    private Long idPublication;
    private Long idCheckout;

    public DeliveryCreateDto(AddressGetDto deliveryAddress, Integer quantity, Long idPublication, Long idCheckout) {
        this.deliveryAddress = deliveryAddress;
        this.quantity = quantity;
        this.idPublication = idPublication;
        this.idCheckout = idCheckout;
    }

    public AddressGetDto getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressGetDto deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Long idPublication) {
        this.idPublication = idPublication;
    }

    public Long getIdCheckout() {
        return idCheckout;
    }

    public void setIdCheckout(Long idCheckout) {
        this.idCheckout = idCheckout;
    }
}
