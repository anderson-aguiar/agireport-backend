package history_service.mappers;


import history_service.dtos.HistoryRequestDTO;
import history_service.dtos.HistoryResponseDTO;
import history_service.model.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {

    public History toEntity(HistoryRequestDTO dto) {

        if (dto == null) return null;

        History history = new History();
        history.setCustomerId(dto.getCustomerId());
        history.setPayload(dto.getPayload());

        return history;
    }

    public HistoryResponseDTO toDto(History entity) {

        if (entity == null) return null;

        HistoryResponseDTO dto = new HistoryResponseDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setPayload(entity.getPayload());

        return dto;
    }
}
