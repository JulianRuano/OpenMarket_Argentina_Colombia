package ar.edu.unlp.pas.cart.services.implementations;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.unlp.pas.cart.dtos.Publication.PublicationGetDto;
import ar.edu.unlp.pas.cart.services.interfaces.IProductService;
import ar.edu.unlp.pas.cart.services.storage.TokenStorage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

@Service
public class PublicationService implements IProductService {

    private final String productUrl = System.getenv("PRODUCT_URL");

    @Override
    public List<PublicationGetDto> getPublicationByIds(Set<Long> ids) {
        try {
            List<PublicationGetDto> publications = new ArrayList<>();
            for (Long id : ids) {
                URI url = new URI(
                        this.productUrl + "/api/publications/" + id);
                String token = TokenStorage.getToken();
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);
                RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, url);
                ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
                String responseBody = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                PublicationGetDto pub = new PublicationGetDto(jsonNode);
                publications.add(pub);
            }
            return publications;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
