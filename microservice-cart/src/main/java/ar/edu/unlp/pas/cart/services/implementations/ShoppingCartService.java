package ar.edu.unlp.pas.cart.services.implementations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.pas.cart.dtos.Publication.PublicationGetDto;
import ar.edu.unlp.pas.cart.dtos.ShoppingCart.ShoppingCartDto;
import ar.edu.unlp.pas.cart.models.CartItem;
import ar.edu.unlp.pas.cart.models.ShoppingCart;
import ar.edu.unlp.pas.cart.repositories.JpaCartItemRepository;
import ar.edu.unlp.pas.cart.repositories.JpaShoppingCartRepository;
import ar.edu.unlp.pas.cart.services.interfaces.IShoppingCartService;

@Service
@Transactional
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private JpaShoppingCartRepository shoppingCartRepository;

    @Autowired
    private JpaCartItemRepository cartItemRepository;

    @Autowired
    private PublicationService publicationService;

    @Override
    public ShoppingCartDto getShoppingCartDto(Long personId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonId(personId.longValue());
        if (shoppingCart == null) {
            shoppingCart = this.createShoppingCart(personId);
        }
        Set<Long> publicationsIds = shoppingCart.getCartItems().stream().map(CartItem::getPublicationId)
                .collect(Collectors.toSet());
        List<PublicationGetDto> publications = (List<PublicationGetDto>) publicationService
                .getPublicationByIds(publicationsIds);
        ShoppingCartDto shoppingCarDto = new ShoppingCartDto(shoppingCart.getId(), publications);
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            for (PublicationGetDto publication : publications) {
                if (cartItem.getPublicationId().equals(publication.getId())) {
                    publication.setQuantity(cartItem.getQuantity());
                }
            }
        }
        return shoppingCarDto;
    }

    @Override
    public Boolean clearShoppingCart(Long personId) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonId(personId.longValue());
        if (shoppingCart == null) {
            return false;
        }
        cartItemRepository.deleteByShoppingCartId(shoppingCart.getId());
        return true;
    }

    @Override
    public Boolean clearShoppingCartForCheckout(Long shoppingCartId) {
        cartItemRepository.setShoppingCartAsCheckedOut(shoppingCartId);
        return true;
    }

    @Override
    public Boolean addProductToShopingCart(Long personId, Long publicationId, int quantity) {
        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findByPersonId(personId.longValue());
            if (shoppingCart == null) {
                shoppingCart = this.createShoppingCart(personId);
            }
            CartItem cartItem = cartItemRepository.findByPublicationIdAndShoppingCartId(publicationId,
                    shoppingCart.getId());
            if (cartItem == null) {
                cartItem = new CartItem();
            }
            cartItem.setPublicationId(publicationId);
            cartItem.setQuantity(quantity);
            cartItem.setShoppingCart(shoppingCart);
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean removeProductFromShoppingCart(Long personId, Long publicationId) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonId(personId.longValue());
        if (shoppingCart == null) {
            return false;
        }
        cartItemRepository.deleteByPublicationIdAndShoppingCartId(publicationId, shoppingCart.getId());
        shoppingCartRepository.save(shoppingCart);
        return true;
    }

    private ShoppingCart createShoppingCart(Long personId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPersonId(personId);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart getShoppingCartByPersonId(Long personId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByPersonId(personId);
        if (shoppingCart == null) {
            shoppingCart = this.createShoppingCart(personId);
        }
        return shoppingCart;
    }

}