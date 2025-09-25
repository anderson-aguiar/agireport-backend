package analytic_service.mapper;

import analytic_service.dto.AnalyticResponseDTO;
import analytic_service.model.Analytic;
import org.springframework.stereotype.Component;

@Component
public class AnalyticMapper {

    public AnalyticResponseDTO toDto(Analytic entity) {
        if (entity == null) return null;

        AnalyticResponseDTO dto = new AnalyticResponseDTO();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClientId());
        dto.setScore(entity.getScore());
        dto.setTypeOfRisk(entity.getTypeOfRisk());

        return dto;
    }
}
