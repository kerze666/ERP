package pl.inf.erp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inf.erp.model.Invoice;
import pl.inf.erp.model.InvoiceType;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByType(InvoiceType type);
}
