package ar.edu.unlp.pas.cart.services.implementations;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;
import ar.edu.unlp.pas.cart.dtos.Delivery.DeliveryCreateDto;
import ar.edu.unlp.pas.cart.models.CartItem;
import ar.edu.unlp.pas.cart.models.Checkout;
import ar.edu.unlp.pas.cart.services.interfaces.IDeliveryService;
import ar.edu.unlp.pas.cart.services.storage.TokenStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DeliveryService implements IDeliveryService {

    private final String deliveryUrl = System.getenv("DELIVERY_URL");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean createDeliveries(Checkout checkout, AddressGetDto deliveryAddressDto) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String token = TokenStorage.getToken();
        boolean allDeliveriesCreated = true;

        for (CartItem cartItem : checkout.getCartItems()) {
            DeliveryCreateDto deliveryCreateDto = new DeliveryCreateDto(deliveryAddressDto, cartItem.getQuantity(), cartItem.getPublicationId(), checkout.getId());
            try {
                String requestBody = objectMapper.writeValueAsString(deliveryCreateDto);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(deliveryUrl + "/api/delivery"))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != HttpURLConnection.HTTP_OK) allDeliveriesCreated = false;
            } catch (IOException | InterruptedException e) {
                return false;
            }
        }
        return allDeliveriesCreated;
    }
}
