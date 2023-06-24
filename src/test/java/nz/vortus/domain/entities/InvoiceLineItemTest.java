package nz.vortus.domain.entities;

import nz.vortus.domain.entities.InvoiceLineItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceLineItemTest {

    @Test
    void createNewInvoiceLineItem(){
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        assertNotNull(invoiceLineItem);
    }
    @Test
    void createNewInvoiceLineItemWithId(){
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        assertNotNull(invoiceLineItem.getId());
    }
    @Test
    void create2NewInvoiceLineItemsWithDifferentIds(){
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        InvoiceLineItem invoiceLineItem2 = new InvoiceLineItem();
        assertNotEquals(invoiceLineItem.getId(), invoiceLineItem2.getId());
    }

    @Test
    void getIdTwice_ReturnSameId(){
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        String id = invoiceLineItem.getId();
        assertEquals(id, invoiceLineItem.getId());
    }



}