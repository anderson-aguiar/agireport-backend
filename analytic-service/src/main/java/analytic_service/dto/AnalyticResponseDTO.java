package analytic_service.dto;

import java.time.LocalDateTime;

public class AnalyticResponseDTO {

    private Long id;

    private Long clientId;
    private Integer score;
    private String typeOfRisk;
    private LocalDateTime onCreate;

    public AnalyticResponseDTO() {
    }

    public AnalyticResponseDTO(Long id, Long clienteId, Integer score, String typeOfRisk, LocalDateTime onCreate) {
        this.id = id;
        this.clientId = clienteId;
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
