package pl.inf.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inf.erp.model.Contractor;
import pl.inf.erp.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
