package pl.inf.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inf.erp.model.Contractor;
import pl.inf.erp.repository.ContractorRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/api")
public class ContractorController {

    private ContractorRepository contractorRepository;

    public ContractorController(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @GetMapping(value = "/contractor")
    public ResponseEntity getContractors() {
        return ResponseEntity.ok(contractorRepository.findAll());
    }

    @GetMapping(value = "/contractor/{id}")
    public ResponseEntity getContractor(@PathVariable Long id) {
        Optional<Contractor> contractor = contractorRepository.findById(id);
        if (contractor.isPresent()) {
            return ResponseEntity.ok(contractor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/contractor")
    public ResponseEntity getHello(@RequestBody Contractor contractor) {
        return ResponseEntity.ok(contractorRepository.save(contractor));
    }
}
