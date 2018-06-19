package pl.inf.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal cost;

    public Date purchaseOfData;

    private double warranty;

    @ManyToMany(mappedBy = "products",fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JsonIgnore
    private Set<Invoice> invoice;
}
