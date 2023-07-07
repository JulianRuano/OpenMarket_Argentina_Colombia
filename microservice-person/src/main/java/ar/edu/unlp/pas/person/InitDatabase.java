package ar.edu.unlp.pas.person;

import ar.edu.unlp.pas.person.dtos.address.AddressCreateDto;
import ar.edu.unlp.pas.person.dtos.person.PersonCreateDto;
import ar.edu.unlp.pas.person.enums.UserRole;
import ar.edu.unlp.pas.person.services.implementations.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;

@Component
@Transactional
public class InitDatabase implements ApplicationRunner {

    @Autowired
    private PersonService pService;

    @Override
    public void run(ApplicationArguments args) {

        try {
            Random rand = new Random();

            AddressCreateDto address1 = new AddressCreateDto(
                    "Wallaby",
                    rand.nextInt(),
                    rand.nextInt(),
                    "-",
                    "Toronto",
                    "Ontario",
                    "Canada");

            AddressCreateDto address2 = new AddressCreateDto(
                    "P. Sherman",
                    rand.nextInt(),
                    rand.nextInt(),
                    "1C",
                    "Vancouver",
                    "British Columbia",
                    "Canada");

            AddressCreateDto address3 = new AddressCreateDto(
                    "44",
                    rand.nextInt(),
                    rand.nextInt(),
                    "110",
                    "Calgary",
                    "Alberta",
                    "Canada");

            AddressCreateDto address4 = new AddressCreateDto(
                    "77",
                    rand.nextInt(),
                    rand.nextInt(),
                    "9a",
                    "La Plata",
                    "Buenos Aires",
                    "Argentina");

            HashSet<AddressCreateDto> set1 = new HashSet<>();
            set1.add(address1);
            set1.add(address2);

            HashSet<AddressCreateDto> set2 = new HashSet<>();
            set2.add(address3);
            set2.add(address2);

            HashSet<AddressCreateDto> set3 = new HashSet<>();
            set3.add(address4);

            PersonCreateDto p1 = new PersonCreateDto("Carlos", "Perez", LocalDate.parse("1993-02-16"),
                    "carlo@gmail.com", UserRole.ADMIN.getRole(), "123", address1, set1);
            PersonCreateDto p2 = new PersonCreateDto("Juan", "Perez", LocalDate.parse("1993-02-16"),
                    "juan@gmail.com",
                    UserRole.USER.getRole(), "123", address2, set2);
            PersonCreateDto p3 = new PersonCreateDto("Pedro", "Perez", LocalDate.parse("1997-02-16"),
                    "pedro@gmail.com", UserRole.USER.getRole(), "123", address3, set2);
            PersonCreateDto p4 = new PersonCreateDto("Roman", "Seller", LocalDate.parse("1969-05-24"),
                    "seller@gmail.com", UserRole.SELLER.getRole(), "123", address4, set3);
            PersonCreateDto p5 = new PersonCreateDto("Juan", "Rodriguez", LocalDate.parse("1996-05-24"),
                    "juanrodriguez@gmail.com", UserRole.DELIVERER.getRole(), "123", address4, set3);

            pService.createPerson(p1);
            pService.createPerson(p2);
            pService.createPerson(p3);
            pService.createPerson(p4);
            pService.createPerson(p5);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
