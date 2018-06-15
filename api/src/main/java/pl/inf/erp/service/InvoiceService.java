package pl.inf.erp.service;

import org.springframework.stereotype.Service;
import pl.inf.erp.model.CostInvoice;
import pl.inf.erp.model.ServiceInvoice;
import pl.inf.erp.repository.CostInvoiceRepository;
import pl.inf.erp.repository.ServiceInvoiceRepository;

import java.util.Optional;

@Service
public class InvoiceService {

    private CostInvoiceRepository costInvoiceRepository;

    private ServiceInvoiceRepository serviceInvoiceRepository;

    public InvoiceService(CostInvoiceRepository costInvoiceRepository, ServiceInvoiceRepository serviceInvoiceRepository) {
        this.costInvoiceRepository = costInvoiceRepository;
        this.serviceInvoiceRepository = serviceInvoiceRepository;
    }

    public CostInvoice createCostInvoice(CostInvoice invoice){
        return costInvoiceRepository.save(invoice);
    }

    public ServiceInvoice createServiceInvoice(ServiceInvoice invoice){
        return serviceInvoiceRepository.save(invoice);
    }
    public Optional<CostInvoice> getCostInvoice(Long id){
        return costInvoiceRepository.findById(id);
    }

    public Optional<ServiceInvoice> getServiceInvoice(Long id){
        return serviceInvoiceRepository.findById(id);
    }
}
