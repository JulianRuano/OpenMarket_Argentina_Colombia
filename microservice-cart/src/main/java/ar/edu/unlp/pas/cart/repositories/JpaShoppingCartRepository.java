package ar.edu.unlp.pas.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.pas.cart.models.ShoppingCart;

@Repository
public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    // find one by person id in SQL
    @Query(value = "SELECT * FROM shopping_cart WHERE person_id = ?1", nativeQuery = true)
    ShoppingCart findByPersonId(Long personId);

}
