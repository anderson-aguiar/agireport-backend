package analytic_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnalyticResponseDTO {

    private Long id;

    private Long clientId;
    private LocalDate customerSince;
    private Integer score;
    private String typeOfRisk;
    private LocalDateTime onCreate;

    public AnalyticResponseDTO() {
    }

    public AnalyticResponseDTO(Long id, Long clienteId, LocalDate customerSince, Integer score, String typeOfRisk, LocalDateTime onCreate) {
        this.id = id;
        this.clientId = clienteId;
        this.customerSince = customerSince;
        this.score = score;
        this.typeOfRisk = typeOfRisk;
        this.onCreate = onCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clienteId) {
        this.clientId = clienteId;
    }

    public LocalDate getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTypeOfRisk() {
        return typeOfRisk;
    }

    public void setTypeOfRisk(String typeOfRisk) {
        this.typeOfRisk = typeOfRisk;
    }

    public LocalDateTime getOnCreate() {
        return onCreate;
    }

    public void setOnCreate(LocalDateTime onCreate) {
        this.onCreate = onCreate;
    }
}
