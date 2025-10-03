package analytic_service.mapper;

import analytic_service.dto.AnalyticResponseCompletedDTO;
import analytic_service.dto.AnalyticResponseDefaultDTO;
import analytic_service.model.Analytic;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AnalyticMapper {

    public AnalyticResponseCompletedDTO toCompleteDto(Analytic entity, LocalDate onCreateDate) {
        if (entity == null) return null;

        AnalyticResponseCompletedDTO dto = new AnalyticResponseCompletedDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getClientId());
        dto.setScore(entity.getScore());
        dto.setTypeOfRisk(entity.getTypeOfRisk());
        dto.setOnCreate(entity.getOnCreate());
        dto.setCustomerSince(onCreateDate);
        dto.setStatus(entity.getStatus());

        return dto;
    }
    public AnalyticResponseDefaultDTO toDefaultDto(Analytic entity, LocalDate onCreateDate) {
        if (entity == null) return null;

        AnalyticResponseDefaultDTO dto = new AnalyticResponseDefaultDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getClientId());
        dto.setOnCreate(entity.getOnCreate());
        dto.setCustomerSince(onCreateDate);
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
