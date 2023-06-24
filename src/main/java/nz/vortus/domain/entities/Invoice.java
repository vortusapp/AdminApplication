package nz.vortus.domain.entities;

import java.util.UUID;

public class Invoice {
    private String id=String.valueOf(UUID.randomUUID());
    private String xeroId;
    private String shopifyId;

    public String getId() { return this.id; }

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
}