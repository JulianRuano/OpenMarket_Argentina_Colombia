package ar.edu.unlp.pas.cart.services.implementations;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;
import ar.edu.unlp.pas.cart.dtos.Checkout.CheckoutGetDto;
import ar.edu.unlp.pas.cart.dtos.Person.PersonGetDto;
import ar.edu.unlp.pas.cart.dtos.Publication.PublicationGetDto;
import ar.edu.unlp.pas.cart.models.CartItem;
import ar.edu.unlp.pas.cart.models.Checkout;
import ar.edu.unlp.pas.cart.models.ShoppingCart;
import ar.edu.unlp.pas.cart.repositories.JpaCheckoutRepository;
import ar.edu.unlp.pas.cart.services.interfaces.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CheckoutService implements ICheckoutService {

    @Autowired
    private JpaCheckoutRepository checkoutRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private DeliveryService deliveryService;

    @Override
    public Checkout createCheckout(long personId) {
        ShoppingCart cart = shoppingCartService.getShoppingCartByPersonId(personId);

        if (cart.getCartItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El carrito de compra está vacío");
        }

        AddressGetDto deliveryAddressGetDto = addressService.getShippingAddressDtoByPersonId(personId);
        AddressGetDto billingAddressGetDto = addressService.getBillingAddressDtoByPersonId(personId);

        Checkout checkout = new Checkout(new Date(), personId, deliveryAddressGetDto.getId(),
                billingAddressGetDto.getId(), cart.getCartItems());
        checkoutRepository.save(checkout);
        shoppingCartService.clearShoppingCartForCheckout(cart.getId());
        if (!deliveryService.createDeliveries(checkout, deliveryAddressGetDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falló la creación de los deliveries necesarios");
        }

        return checkout;
    }

    @Override
    public Checkout getCheckoutById(long id) {
        return checkoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el checkout"));
    }

    @Override
    public List<Checkout> getAllCheckoutsByPersonId(long personId) {
        return checkoutRepository.findByPersonId(personId);
    }

    @Override
    public List<CheckoutGetDto> getAllCheckoutsDtoByPersonId(long personId) {
        List<Checkout> checkoutList = checkoutRepository.findByPersonId(personId);
        if (checkoutList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La persona no posee ningún checkout");
        }

        return checkoutList.stream()
                .map(checkout -> getCheckoutDtoById(checkout.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CheckoutGetDto getCheckoutDtoById(long id) {
        Checkout checkout = checkoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el checkout"));

        AddressGetDto addressGetDto = addressService.getShippingAddressDtoByPersonId(checkout.getPersonId());
        PersonGetDto personGetDto = personService.getPersonDtoById(checkout.getPersonId());
        Set<Long> publicationIds = checkout.getCartItems()
                .stream()
                .map(CartItem::getId)
                .collect(Collectors.toSet());
        List<PublicationGetDto> publicationGetDtoList = publicationService.getPublicationByIds(publicationIds);

        return new CheckoutGetDto(
                checkout.getId(),
                checkout.getDateCheckout(),
                addressGetDto,
                personGetDto,
                publicationGetDtoList);
    }
}
