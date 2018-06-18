package pl.inf.erp.service;

import org.springframework.stereotype.Service;
import pl.inf.erp.model.Invoice;
import pl.inf.erp.repository.CostInvoiceRepository;
import pl.inf.erp.repository.InvoiceRepository;

import java.util.Optional;

@Service
public class InvoiceService {


    private InvoiceRepository invoiceRepository;

    public InvoiceService(CostInvoiceRepository costInvoiceRepository, InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createCostInvoice(Invoice invoice){
        return costInvoiceRepository.save(invoice);
    }

    public ServiceInvoice createServiceInvoice(ServiceInvoice invoice){
        return invoiceRepository.save(invoice);
    }
    public Optional<Invoice> getCostInvoice(Long id){
        return costInvoiceRepository.findById(id);
    }

    public Optional<ServiceInvoice> getServiceInvoice(Long id){
        return invoiceRepository.findById(id);
    }
}
