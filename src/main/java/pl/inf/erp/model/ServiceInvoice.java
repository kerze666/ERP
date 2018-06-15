//package pl.inf.erp.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class ServiceInvoice {
//
//    @Id
//    protected Long id;
//
//    protected LocalDate dataOfIssue;
//
//    protected LocalDate deadline;
//
//    protected Contractor contractor;
//
//    protected boolean isPaid;
//
//    protected BigDecimal amount;
//
//    @ManyToMany
//    private List<Service> services;
//}
