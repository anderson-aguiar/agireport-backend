package history_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public class HistoryRequestDTO {

    @Schema(description = "ID do cliente", example = "6")
    private Long customerId;
    @Schema(description = "Payload de evento",
            example = "{\"eventType\": \"ATRASO\", \"value\": 4103.18, \"days_overdue\": 4, \"occurence_date\": \"2024-12-22\"}")
    private Map<String, Object> payload;

    public HistoryRequestDTO() {
    }

    public HistoryRequestDTO(Long customerId, Map<String, Object> payload) {
        this.customerId = customerId;
        this.payload = payload;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
