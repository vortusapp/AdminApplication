package nz.vortus.domain.entities;

import java.util.UUID;

public class InvoiceLineItem {
    private String id=UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

}