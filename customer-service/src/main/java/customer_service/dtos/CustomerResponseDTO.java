package customer_service.dtos;

import java.time.LocalDate;

public class CustomerResponseDTO {

    private Long id;

    private String name;
    private String cpf;
    private LocalDate dateOfbirth;
    private double income;
    private String bankAccount;
    private String gender;
    private String maritalStatus;
    private String jobTitle;

    private AddressResponseDTO addressResponseDTO;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(Long id, String name, String cpf, LocalDate dateOfbirth,
                               double income, String bankAccount, String gender, String maritalStatus,
                               String jobTitle, AddressResponseDTO addressResponseDTO) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.dateOfbirth = dateOfbirth;
        this.income = income;
        this.bankAccount = bankAccount;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.jobTitle = jobTitle;
        this.addressResponseDTO = addressResponseDTO;
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

    public AddressResponseDTO getAddressResponseDTO() {
        return addressResponseDTO;
    }

    public void setAddressResponseDTO(AddressResponseDTO addressResponseDTO) {
        this.addressResponseDTO = addressResponseDTO;
    }
}
