package ar.edu.unlp.pas.cart.services.interfaces;

import ar.edu.unlp.pas.cart.dtos.ShoppingCart.ShoppingCartDto;
import ar.edu.unlp.pas.cart.models.ShoppingCart;

public interface IShoppingCartService {

    ShoppingCartDto getShoppingCartDto(Long personId);

    Boolean clearShoppingCart(Long personId);

    Boolean clearShoppingCartForCheckout(Long shoppingCartId);

    Boolean addProductToShopingCart(Long personId, Long publicationId, int quantity);

    Boolean removeProductFromShoppingCart(Long personId, Long publicationId);

    ShoppingCart getShoppingCartByPersonId(Long personId);

}
