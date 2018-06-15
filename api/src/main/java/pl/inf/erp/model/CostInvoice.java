package pl.inf.erp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostInvoice {

    @Id
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataOfIssue;

    private LocalDate deadline;

    private boolean isPaid;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "id_cont")
    private Contractor contractor;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> product;

}
