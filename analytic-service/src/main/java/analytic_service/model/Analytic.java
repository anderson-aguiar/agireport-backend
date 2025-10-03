package analytic_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_analytic")
@EntityListeners(AuditingEntityListener.class)
public class Analytic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Integer score;
    private String typeOfRisk;
    private String status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime onCreate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime onUpdate;

    @Version
    private Long version;

    public Analytic() {
    }

    public Analytic(Long customerId, Integer score, String typeOfRisk) {
        this.customerId = customerId;
        this.score = score;
        this.typeOfRisk = typeOfRisk;
        this.status = "PROCESSANDO";
    }
    public Analytic(Long customerId){
        this.customerId = customerId;
        this.status = "PROCESSANDO";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return customerId;
    }

    public void setClientId(Long clientId) {
        this.customerId = clientId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOnCreate() {
        return onCreate;
    }

    public LocalDateTime getOnUpdate() {
        return onUpdate;
    }
}
