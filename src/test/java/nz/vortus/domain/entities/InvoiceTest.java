package nz.vortus.domain.entities;

import nz.vortus.domain.entities.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewInvoice(){
        Invoice invoice = new Invoice();
        assertNotNull(invoice);
    }

    @Test
    void createNewInvoiceWithId(){
        Invoice invoice = new Invoice();
        assertNotNull(invoice.getId());
    }

    @Test
    void create2NewInvoicesWithDifferentIds(){
        Invoice invoice = new Invoice();
        Invoice invoice2 = new Invoice();
        assertNotEquals(invoice.getId(), invoice2.getId());
    }

    @Test
    void setXeroInvoiceId_getsXeroInvoiceId(){
        Invoice invoice = new Invoice();
        String xeroInvoiceId = "1234";
        invoice.setXeroId(xeroInvoiceId);
        assertEquals(xeroInvoiceId, invoice.getXeroId());
    }

    @Test
    void setShopifyInvoiceId_getsShopifyInvoiceId(){
        Invoice invoice = new Invoice();
        String shopifyInvoiceId = "1234";
        invoice.setShopifyId(shopifyInvoiceId);
        assertEquals(shopifyInvoiceId, invoice.getShopifyId());
    }
}