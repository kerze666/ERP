package pl.inf.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inf.erp.model.CostInvoice;
import pl.inf.erp.model.ServiceInvoice;
import pl.inf.erp.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/api/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping(value = "/cost")
    public ResponseEntity createCostInvoice(@RequestBody CostInvoice costInvoice) {
        return ResponseEntity.ok(invoiceService.createCostInvoice(costInvoice));
    }

    @PostMapping(value = "/service")
    public ResponseEntity createServiceInvoice(@RequestBody CostInvoice costInvoice) {
        return ResponseEntity.ok(invoiceService.createCostInvoice(costInvoice));
    }

    @GetMapping(value = "/cost/{id}")
    public ResponseEntity getCostInvoice(@PathVariable("id") Long id) {
        Optional<CostInvoice> invoice = invoiceService.getCostInvoice(id);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/service/{id}")
    public ResponseEntity getServiceInvoice(@PathVariable("id") Long id) {
        Optional<ServiceInvoice> invoice = invoiceService.getServiceInvoice(id);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
