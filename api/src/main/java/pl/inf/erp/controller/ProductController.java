package pl.inf.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inf.erp.model.Contractor;
import pl.inf.erp.model.Product;
import pl.inf.erp.repository.ContractorRepository;
import pl.inf.erp.repository.ProductRepository;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/api")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/product")
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        Optional<Product> contractor = productRepository.findById(id);
        if (contractor.isPresent()) {
            return ResponseEntity.ok(contractor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/product")
    public ResponseEntity createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }
}
