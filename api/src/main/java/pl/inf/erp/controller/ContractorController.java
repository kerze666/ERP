package pl.inf.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inf.erp.model.Contractor;
import pl.inf.erp.repository.ContractorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/api")
@Slf4j
public class ContractorController {

    private ContractorRepository contractorRepository;

    public ContractorController(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @GetMapping(value = "/contractor")
    public ResponseEntity getContractors() {
        List<Contractor> contractorList = contractorRepository.findAll();
        log.info("Get all contractors");
        return ResponseEntity.ok(contractorList);
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
