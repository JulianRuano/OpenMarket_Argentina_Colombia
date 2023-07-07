package ar.edu.unlp.pas.cart.services.implementations;

import ar.edu.unlp.pas.cart.dtos.Address.AddressGetDto;
import ar.edu.unlp.pas.cart.services.interfaces.IAddressService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unlp.pas.cart.services.storage.TokenStorage;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class AddressService implements IAddressService {

    private final String personUrl = System.getenv("PERSON_URL");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AddressGetDto getShippingAddressDtoByPersonId(long personId) {
        try {
            URL url = new URL(this.personUrl + "/api/address/shippingAddress?personId=" + personId);
            return requestAddressGetDto(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to retrieve shipping address: Bad URL");
        }
    }

    @Override
    public AddressGetDto getBillingAddressDtoByPersonId(long personId) {
        try {
            URL url = new URL(this.personUrl + "/api/address/billingAddress?personId=" + personId);
            return requestAddressGetDto(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to retrieve shipping address: Bad URL");
        }
    }

    private AddressGetDto requestAddressGetDto(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + TokenStorage.getToken());
            con.connect();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JsonNode responseJson;
                try (InputStream inputStream = con.getInputStream()) {
                    responseJson = objectMapper.readValue(inputStream, JsonNode.class);
                }
                return objectMapper.treeToValue(responseJson, AddressGetDto.class);
            } else {
                throw new MalformedURLException();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to retrieve shipping address: Bad URL");
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve shipping address: IO error");
        }
    }
}
