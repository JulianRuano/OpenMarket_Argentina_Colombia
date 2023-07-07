package ar.edu.unlp.pas.cart.Controller;

import ar.edu.unlp.pas.cart.dtos.ShoppingCart.AddProductDto;
import ar.edu.unlp.pas.cart.dtos.ShoppingCart.ShoppingCartDto;
import ar.edu.unlp.pas.cart.services.implementations.ShoppingCartService;
import ar.edu.unlp.pas.cart.services.storage.TokenStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ShoppingCartController {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/api/shopping-cart")
    public ResponseEntity<ShoppingCartDto> getShoppingCart(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        TokenStorage.setToken(jwtToken);
        long personId = Long.parseLong(String.valueOf(auth.getPrincipal()));
        ShoppingCartDto shoppingCart = shoppingCartService.getShoppingCartDto(personId);
        TokenStorage.clearToken();
        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping("/api/shopping-cart/clear")
    public ResponseEntity<Boolean> clearShoppingCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long personId = Long.parseLong(String.valueOf(auth.getPrincipal()));
        return ResponseEntity.ok(shoppingCartService.clearShoppingCart(personId));
    }

    @PostMapping("/api/shopping-cart")
    public ResponseEntity<Boolean> addProductToShoppingCart(@RequestBody AddProductDto body) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long personId = Long.parseLong(String.valueOf(auth.getPrincipal()));
        long publicationIdLong = Long.parseLong(body.publicationId);
        return ResponseEntity.ok(shoppingCartService.addProductToShopingCart(personId,
                publicationIdLong, body.quantity));
    }

    @DeleteMapping("/api/shopping-cart")
    public ResponseEntity<Boolean> removeProductFromShoppingCart(@RequestBody AddProductDto body) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long personId = Long.parseLong(String.valueOf(auth.getPrincipal()));
        long publicationIdLong = Long.parseLong(body.publicationId);
        return ResponseEntity.ok(shoppingCartService.removeProductFromShoppingCart(personId,
                publicationIdLong));
    }

}
