package pl.inf.erp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.inf.erp.model.Invoice;
import pl.inf.erp.model.InvoiceType;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByType(InvoiceType type);
}
