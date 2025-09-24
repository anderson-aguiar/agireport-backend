package customer_service.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class CustomerRequestDTO {
    @NotBlank(message = "O nome precisa ser informado")
    private String name;

    @NotBlank(message = "CPF precisa ser informado")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    private LocalDate dateOfbirth;
    @Positive(message = "A renda precisa ser um valor positivo")
    private double income;

    @NotBlank(message = "Conta obrigatória")
    private String bankAccount;

    private String gender;

    private String maritalStatus;
    private String jobTitle;
    @Valid
    private AddressRequestDTO address;

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String name, String cpf,
                              LocalDate dateOfbirth, double income, String bankAccount, String gender,
                              String maritalStatus, String jobTitle, AddressRequestDTO address) {
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

    public AddressRequestDTO getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }
}
