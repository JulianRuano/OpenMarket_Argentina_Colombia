package ar.edu.unlp.pas.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.pas.cart.models.CartItem;

@Repository
public interface JpaCartItemRepository extends JpaRepository<CartItem, Long> {

    @Modifying
    @Query(value = "DELETE FROM cart_item WHERE shopping_cart_id = ?1", nativeQuery = true)
    void deleteByShoppingCartId(Long shoppingCartId);

    @Modifying
    @Query("UPDATE cart_item SET shopping_cart_id = NULL WHERE id = ?1")
    void setShoppingCartAsCheckedOut(Long shoppingCartId);

    @Modifying
    @Query(value = "DELETE FROM cart_item WHERE publication_id = ?1 AND shopping_cart_id = ?2", nativeQuery = true)
    void deleteByPublicationIdAndShoppingCartId(Long publicationId, Long shoppingCartId);

    @Query(value = "SELECT * FROM cart_item WHERE publication_id = ?1 AND shopping_cart_id = ?2", nativeQuery = true)
    CartItem findByPublicationIdAndShoppingCartId(Long publicationId, Long shoppingCartId);
}
