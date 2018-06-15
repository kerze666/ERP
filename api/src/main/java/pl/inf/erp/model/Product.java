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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long cost;

    public LocalDate purchaseOfData;

    private double warranty;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<CostInvoice> invoice;
}
