package ar.edu.unlp.pas.person.repositories;

import ar.edu.unlp.pas.person.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);

    Optional<Person> findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    // Find all persons with addresses join
    @Query("SELECT DISTINCT p FROM Person p LEFT JOIN p.shippingAddresses")
    List<Person> findAllWithAddresses();

    @Query("SELECT DISTINCT p FROM Person p LEFT JOIN p.shippingAddresses WHERE p.email = ?1")
    Person findByEmailWithAddresses(String email);
}
