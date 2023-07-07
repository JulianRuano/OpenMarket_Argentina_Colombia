package ar.edu.unlp.pas.product.domain.service;

import ar.edu.unlp.pas.product.application.ports.input.IPublicationManager;
import ar.edu.unlp.pas.product.application.ports.output.IPublicationOutputPort;
import ar.edu.unlp.pas.product.domain.exception.InsufficientStockException;
import ar.edu.unlp.pas.product.domain.exception.InvalidQuantityException;
import ar.edu.unlp.pas.product.domain.exception.PublicationNotFoundException;
import ar.edu.unlp.pas.product.domain.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PublicationService implements IPublicationManager {

    private final IPublicationOutputPort publicationOutputPort;

    @Autowired

    public PublicationService(IPublicationOutputPort publicationOutputPort) {
        this.publicationOutputPort = publicationOutputPort;
    }

    @Override
    public Publication createPublication(Publication publication) {
      publication=publicationOutputPort.create(publication);
        return publication;
    }

    @Override
    public void suspendPublication(Long publicationId) {
      publicationOutputPort.suspendPublication(publicationId);
    }

    @Override
    public Publication findById(Long idPublication) {
        return publicationOutputPort.findById(idPublication);
    }

    @Override
    public List<Publication> findAll() {
        return publicationOutputPort.findAll();
    }

    @Override
    public Publication decreaseStock(Long idPublication, Integer quantity) {
        Publication publication=publicationOutputPort.findById(idPublication);
        if(publication==null){
            throw new PublicationNotFoundException("Publicacion no encontrada");
        }
        int stock=publication.getStock();
        if(stock<quantity){
            throw  new InsufficientStockException("No hay suficiente stock");
        }else if(quantity<0) {
            throw new InvalidQuantityException("La cantidad debe ser mayor a 0");
        }
        int newStock = stock - quantity;
        return publicationOutputPort.decreaseStock(idPublication,newStock);
    }

    @Override
    public List<Publication> filterPublications(String name, String description, Double minPrice, Double maxPrice, String category,String country) {
        return publicationOutputPort.filterPublications(name,description,minPrice,maxPrice,category,country);
    }




}
