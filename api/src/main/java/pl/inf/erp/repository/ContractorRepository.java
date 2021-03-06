package pl.inf.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inf.erp.model.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
