package analytic_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnalyticResponseCompletedDTO extends AnalyticResponseDefaultDTO {


    private Integer score;
    private String typeOfRisk;

    public AnalyticResponseCompletedDTO() {
    }

    public AnalyticResponseCompletedDTO(Integer score, String typeOfRisk) {
        this.score = score;
        this.typeOfRisk = typeOfRisk;
    }

    public AnalyticResponseCompletedDTO(Long id, Long customerId, LocalDate customerSince, LocalDateTime onCreate, String status, Integer score, String typeOfRisk) {
        super(id, customerId, customerSince, onCreate, status);
        this.score = score;
        this.typeOfRisk = typeOfRisk;
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
