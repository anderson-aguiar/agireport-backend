package analytic_service.dto;

public class AnalyticResponseDTO {

    private Long id;

    private Long clientId;
    private Integer score;
    private String typeOfRisk;

    public AnalyticResponseDTO() {
    }

    public AnalyticResponseDTO(Long id, Long clienteId, Integer score, String typeOfRisk) {
        this.id = id;
        this.clientId = clienteId;
        this.score = score;
        this.typeOfRisk = typeOfRisk;
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
}
