package ar.edu.unlp.pas.delivery.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong delivery");
    }

    /*
     * Ejemplo de como hacer una petición http simple a otro container
     */
    @GetMapping("/api/ping-cart")
    public ResponseEntity<String> pingPerson() {
        try {
            String cartUrl = System.getenv("PERSON_URL");
            URL url = new URL(cartUrl + "/api/ping");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return ResponseEntity.status(responseCode).body(
                        "Error: " + responseCode + " " + con.getResponseMessage() + " " +
                                con.getContent().toString());
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return ResponseEntity.ok(response.toString());
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}