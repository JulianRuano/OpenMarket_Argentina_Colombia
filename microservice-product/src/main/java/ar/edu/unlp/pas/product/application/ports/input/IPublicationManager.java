package ar.edu.unlp.pas.product.application.ports.input;

import ar.edu.unlp.pas.product.domain.models.Product;
import ar.edu.unlp.pas.product.domain.models.Publication;

import java.util.List;

public interface  IPublicationManager {
   Publication createPublication(Publication publication);
    void suspendPublication(Long publicationId);
    Publication findById(Long idPublication);
    List<Publication> findAll();
    Publication decreaseStock(Long idPublication, Integer quantity);
    public List<Publication> filterPublications(String name, String description, Double minPrice, Double maxPrice, String category, String country);
}
