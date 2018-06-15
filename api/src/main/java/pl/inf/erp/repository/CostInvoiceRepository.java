package pl.inf.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inf.erp.model.Contractor;
import pl.inf.erp.model.CostInvoice;

public interface CostInvoiceRepository extends JpaRepository<CostInvoice, Long> {
}
