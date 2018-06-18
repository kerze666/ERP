package pl.inf.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inf.erp.model.Invoice;
import pl.inf.erp.model.InvoiceType;
import pl.inf.erp.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/api/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping(value = "/")
    public ResponseEntity createInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }

    @GetMapping(value = "/type/{type}")
    public ResponseEntity getInvoicesByType(@PathVariable("type") InvoiceType type) {
        return ResponseEntity.ok(invoiceService.getInvoicesByType(type));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getInvoiceById(@PathVariable("id") Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoice(id);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/type")
    public ResponseEntity getTypes() {
        return ResponseEntity.ok(InvoiceType.values());
    }

}
