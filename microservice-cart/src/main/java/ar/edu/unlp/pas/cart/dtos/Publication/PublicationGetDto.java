package ar.edu.unlp.pas.cart.dtos.Publication;

import com.fasterxml.jackson.databind.JsonNode;

import ar.edu.unlp.pas.cart.dtos.Product.ProductGetDto;

public class PublicationGetDto {

    private Long id;
    private Integer stock;
    private Double sellPrice;
    private Double minPrice;
    private Double maxPrice;
    private Boolean isActive;
    private Integer quantity;
    private ProductGetDto product;

    public PublicationGetDto(JsonNode data) {
        this.id = data.get("publicationId").asLong();
        this.stock = data.get("stock").asInt();
        this.sellPrice = data.get("sellPrice").asDouble();
        this.minPrice = data.get("minPrice").asDouble();
        this.maxPrice = data.get("maxPrice").asDouble();
        this.isActive = data.get("isActive").asBoolean();
        this.product = new ProductGetDto(data.get("product"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ProductGetDto getProduct() {
        return product;
    }

    public void setProduct(ProductGetDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
