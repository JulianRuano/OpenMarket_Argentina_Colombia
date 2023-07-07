package ar.edu.unlp.pas.person.repositories;

import ar.edu.unlp.pas.person.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT DISTINCT a FROM Address a LEFT JOIN a.person p WHERE p.id = ?1")
    List<Address> findByPersonId(long personId);
}
