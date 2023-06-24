package nz.vortus.adapters.dataLayer;

import nz.vortus.domain.entities.Invoice;
import com.xero.models.accounting.Invoices;

import java.util.List;

public class XeroInvoiceRepositoryImpl implements XeroInvoiceRepository {



    public XeroInvoiceRepositoryImpl() {

    }

    @Override
    public List<Invoice> getInvoices() {

        // Fetch invoices from the Xero API and convert them to domain entities
        return null;
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        // Update the invoice in the Xero API
    }
    @Override
    public Invoices getInvoice(String invoiceId) {
        return null;
    }
}
