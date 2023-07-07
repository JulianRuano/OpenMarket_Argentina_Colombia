package ar.edu.unlp.pas.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.pas.cart.models.Checkout;

import java.util.List;

@Repository
public interface JpaCheckoutRepository extends JpaRepository<Checkout, Long> {
        List<Checkout> findByPersonId(long personId);

}
