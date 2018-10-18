package com.example.demo.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@Builder
//@Data
//@Value
public class Product {

    private int id;
    private String name;
    private Category category;
    private ShoeSize shoeSize;
    private ClotheSize clotheSize;
    private Material material;
    private BigDecimal price;
    private String url;

    public Product() {
    }

    public Product(int id, String name, Category category, ShoeSize shoeSize, ClotheSize clotheSize, Material material, BigDecimal price, String url) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.shoeSize = shoeSize;
        this.clotheSize = clotheSize;
        this.material = material;
        this.price = price;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ShoeSize getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(ShoeSize shoeSize) {
        this.shoeSize = shoeSize;
    }

    public ClotheSize getClotheSize() {
        return clotheSize;
    }

    public void setClotheSize(ClotheSize clotheSize) {
        this.clotheSize = clotheSize;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        if (getId() != product.getId()) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getCategory() != null ? !getCategory().equals(product.getCategory()) : product.getCategory() != null)
            return false;
        if (getShoeSize() != null ? !getShoeSize().equals(product.getShoeSize()) : product.getShoeSize() != null)
            return false;
        if (getClotheSize() != null ? !getClotheSize().equals(product.getClotheSize()) : product.getClotheSize() != null)
            return false;
        if (getMaterial() != null ? !getMaterial().equals(product.getMaterial()) : product.getMaterial() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(product.getPrice()) : product.getPrice() != null) return false;
        return getUrl() != null ? getUrl().equals(product.getUrl()) : product.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getShoeSize() != null ? getShoeSize().hashCode() : 0);
        result = 31 * result + (getClotheSize() != null ? getClotheSize().hashCode() : 0);
        result = 31 * result + (getMaterial() != null ? getMaterial().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }
}
