package pl.inf.erp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {

    @Id
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dataOfIssue;

    private Date deadline;

    private boolean isPaid;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_cont")
    private Contractor contractor;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "inv_prod", joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "inv_serv", joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services;
}
