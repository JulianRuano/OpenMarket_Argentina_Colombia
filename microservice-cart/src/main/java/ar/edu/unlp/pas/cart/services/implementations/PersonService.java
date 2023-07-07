package ar.edu.unlp.pas.cart.services.implementations;

import ar.edu.unlp.pas.cart.dtos.Person.PersonGetDto;
import ar.edu.unlp.pas.cart.services.interfaces.IPersonService;
import ar.edu.unlp.pas.cart.services.storage.TokenStorage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class PersonService implements IPersonService {

    private final String personUrl = System.getenv("PERSON_URL");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PersonGetDto getPersonDtoById(long personId) {
        try {
            URL url = new URL(this.personUrl + "/api/person/" + personId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            String token = TokenStorage.getToken();
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.connect();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                JsonNode responseJson;
                try (InputStream inputStream = con.getInputStream()) {
                    responseJson = objectMapper.readValue(inputStream, JsonNode.class);
                }
                return objectMapper.treeToValue(responseJson, PersonGetDto.class);
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
