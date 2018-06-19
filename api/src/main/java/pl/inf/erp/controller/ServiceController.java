package pl.inf.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inf.erp.model.Contractor;
import pl.inf.erp.model.Service;
import pl.inf.erp.repository.ServiceRepository;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/api")
public class ServiceController {

    private ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping(value = "/service")
    public ResponseEntity getServices() {
        return ResponseEntity.ok(serviceRepository.findAll());
    }

    @GetMapping(value = "/service/{id}")
    public ResponseEntity getService(@PathVariable Long id) {
        Optional<Service> contractor = serviceRepository.findById(id);
        if (contractor.isPresent()) {
            return ResponseEntity.ok(contractor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/service")
    public ResponseEntity createService(@RequestBody Service service) {
        return ResponseEntity.ok(serviceRepository.save(service));
    }
}
