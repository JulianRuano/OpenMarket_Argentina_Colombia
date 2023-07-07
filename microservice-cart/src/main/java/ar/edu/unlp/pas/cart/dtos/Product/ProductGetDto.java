package ar.edu.unlp.pas.cart.dtos.Product;

import com.fasterxml.jackson.databind.JsonNode;

public class ProductGetDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String category;

    public ProductGetDto(JsonNode jsonNode) {
        this.id = jsonNode.get("id").asLong();
        this.name = jsonNode.get("name").asText();
        this.price = jsonNode.get("price").asDouble();
        this.description = jsonNode.get("description").asText();
        this.category = jsonNode.get("category").asText();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
