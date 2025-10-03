package analytic_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnalyticResponseDefaultDTO {

    private Long id;
    private Long customerId;
    private LocalDate customerSince;
    private LocalDateTime onCreate;
    private String status;

    public AnalyticResponseDefaultDTO() {
    }

    public AnalyticResponseDefaultDTO(Long id, Long customerId, LocalDate customerSince, LocalDateTime onCreate, String status) {
        this.id = id;
        this.customerId = customerId;
        this.customerSince = customerSince;
        this.onCreate = onCreate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    public LocalDateTime getOnCreate() {
        return onCreate;
    }

    public void setOnCreate(LocalDateTime onCreate) {
        this.onCreate = onCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
