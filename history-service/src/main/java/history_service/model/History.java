package history_service.model;


import history_service.mappers.HashMapConverter;
import jakarta.persistence.*;

import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "tb_history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Convert(converter = HashMapConverter.class) //usa o conversor personalizado para JSON
    private Map<String, Object> payload; //representa o JSON

    public History() {
    }

    public History(Long id, Long customerId, Map<String, Object> payload) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
