package analytic_service.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_analise")
@EntityListeners(AuditingEntityListener.class)
public class Analytic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private Integer score;
    private String typeOfRisk;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime onCreate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime onUpdate;

    public Analytic() {
    }

    public Analytic(Long id, Long clientId, Integer score, String typeOfRisk) {
        this.id = id;
        this.clientId = clientId;
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

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public LocalDateTime getOnUpdate() {
        return onUpdate;
    }
}
