package nz.vortus.domain.entities;

import nz.vortus.domain.valueObjects.Email;

import java.util.UUID;

public class Customer {
    private String id= String.valueOf(UUID.randomUUID());
    private String xeroId;
    private String shopifyId;
    private String companyName;

    private Email email;



    public String getId() {
        return this.id;
    }
    public void setXeroId(String xeroId) {
this.xeroId = xeroId;
    }
    public String getXeroId() {
        return this.xeroId;
    }
    public void setShopifyId(String shopifyId) {
        this.shopifyId = shopifyId;
    }
    public String getShopifyId() {
        return this.shopifyId;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setEmail(String email) {
        this.email = new Email(email);
    }
    public Email getEmail() {
        return this.email;
    }
}