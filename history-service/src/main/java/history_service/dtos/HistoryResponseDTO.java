package history_service.dtos;

import java.util.Map;

public class HistoryResponseDTO {

    private Long id;

    private Long customerId;

    private Map<String, Object> payload;

    public HistoryResponseDTO() {
    }

    public HistoryResponseDTO(Long id, Long customerId, Map<String, Object> payload) {
        this.id = id;
        this.customerId = customerId;
        this.payload = payload;
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

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
