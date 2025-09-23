package customer_service.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private LocalDate dateOfbirth;
    private double income;
    private String bankAccount;
    private String gender;
    private String maritalStatus;
    private String jobTitle;
    private LocalDateTime deletedAt = null;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime onCreate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime onUpdate;

    public Customer() {
    }

    public Customer(Long id, String name, String cpf, LocalDate dateOfbirth, double income,
                    String bankAccount, String gender, String maritalStatus, String jobTitle,
                    Address address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.dateOfbirth = dateOfbirth;
        this.income = income;
        this.bankAccount = bankAccount;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.jobTitle = jobTitle;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(LocalDate dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getOnCreate() {
        return onCreate;
    }

    public LocalDateTime getOnUpdate() {
        return onUpdate;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
