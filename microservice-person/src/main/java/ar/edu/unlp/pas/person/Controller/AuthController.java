package ar.edu.unlp.pas.person.Controller;

import ar.edu.unlp.pas.person.dtos.person.PersonGetDto;
import ar.edu.unlp.pas.person.models.Person;
import ar.edu.unlp.pas.person.models.UserCredentials;
import ar.edu.unlp.pas.person.services.implementations.PersonService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private PersonService personService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserCredentials credentials) {
        try {
            if (personService.authenticateUser(
                    credentials.getUsername(),
                    credentials.getPassword())) {
                Person person = personService.getPersonByEmail(
                        credentials.getUsername());
                PersonGetDto personGetDto = new PersonGetDto(person);
                return ResponseEntity.ok().body(getJWTToken(personGetDto));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private String getJWTToken(PersonGetDto personGetDto) {
        String secretKey = System.getenv("SECRET_KEY");

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
                personGetDto.getRole());

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(String.valueOf(personGetDto.getId()))
                .claim(
                        "authorities",
                        grantedAuthorities
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        return "Bearer " + token;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex
                .getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }
}
