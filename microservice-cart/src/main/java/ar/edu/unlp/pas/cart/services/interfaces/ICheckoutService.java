package ar.edu.unlp.pas.cart.services.interfaces;

import java.util.List;

import ar.edu.unlp.pas.cart.dtos.Checkout.CheckoutGetDto;
import ar.edu.unlp.pas.cart.models.Checkout;

public interface ICheckoutService {
    Checkout createCheckout(long personId);

    Checkout getCheckoutById(long id);

    List<Checkout> getAllCheckoutsByPersonId(long id);

    CheckoutGetDto getCheckoutDtoById(long id);

    List<CheckoutGetDto> getAllCheckoutsDtoByPersonId(long personId);
}
