package history_service.dto;

import java.util.Map;

public class HistoryRequestDTO {

    private Long customerId;
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
