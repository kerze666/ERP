package pl.inf.erp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.inf.erp.model.Invoice;
import pl.inf.erp.model.InvoiceType;
import pl.inf.erp.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.saveAndFlush(invoice);
    }

    public Optional<Invoice> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }

    public List<Invoice> getInvoicesByType(InvoiceType type){
        return invoiceRepository.findAllByType(type);
    }
}
