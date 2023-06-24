package nz.vortus.domain.entities;

import nz.vortus.domain.valueObjects.Price;

import java.util.UUID;

public class Product {
    String id = UUID.randomUUID().toString();
    private String xeroId;
    private String shopifyId;
    private String SKU;
    private String name;
    private String description;
    private Price salesPrice;
    private Price purchasePrice;

    public String getId() {
        return id;
    }

    public void setXeroId(String xeroId) {
        this.xeroId =xeroId;
    }

    public String getXeroId() {
        return xeroId;
    }

    public void setShopifyId(String shopifyId) {
        this.shopifyId=shopifyId;
    }

    public String getShopifyId() {
        return shopifyId;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getSKU() {
        return SKU;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setSalesPrice(Price salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Price getSalesPrice() {
        return salesPrice;
    }

    public void setPurchasePrice(Price purchasePrice) {
    this.purchasePrice = purchasePrice;
    }

    public Price getPurchasePrice() {
        return purchasePrice;
    }
}