package nz.vortus.adapters.dataLayer;

import nz.vortus.domain.entities.Invoice;
import com.xero.models.accounting.Invoices;

import java.util.List;

public interface XeroInvoiceRepository {
    List<Invoice> getInvoices();

    void updateInvoice(Invoice invoice);

    Invoices getInvoice(String invoiceId);
}
