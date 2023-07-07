package ar.edu.unlp.pas.cart.Controller;

import ar.edu.unlp.pas.cart.dtos.Checkout.CheckoutGetDto;
import ar.edu.unlp.pas.cart.models.Checkout;
import ar.edu.unlp.pas.cart.services.implementations.CheckoutService;
import ar.edu.unlp.pas.cart.services.storage.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/checkouts")
@RestController
public class CheckOutController {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping()
    public ResponseEntity<List<CheckoutGetDto>> listCheckoutsByPersonId(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        long personId = Long.parseLong(String.valueOf(auth.getPrincipal()));
        try {
            return ResponseEntity.ok(checkoutService.getAllCheckoutsDtoByPersonId(personId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckoutGetDto> getCheckoutsById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(checkoutService.getCheckoutDtoById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<CheckoutGetDto> createCheckout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        TokenStorage.setToken(jwtToken);

        long personId = Long.parseLong(String.valueOf(auth.getPrincipal()));

        try {
            Checkout checkout = checkoutService.createCheckout(personId);
            return ResponseEntity.ok(checkoutService.getCheckoutDtoById(checkout.getId()));
        } catch (HttpClientErrorException.BadRequest br) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
