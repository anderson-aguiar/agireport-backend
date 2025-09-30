package analytic_service.services;

import analytic_service.dto.HistoryResponseDTO;
import analytic_service.repositories.AnalyticRepository;
import analytic_service.utils.WebClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnalyticService {

    private final AnalyticRepository analyticRepository;

    private final WebClientUtil webClientUtil;

    public AnalyticService(AnalyticRepository analyticRepository, WebClientUtil webClientUtil) {
        this.analyticRepository = analyticRepository;
        this.webClientUtil = webClientUtil;
    }


    @Transactional
    public List<HistoryResponseDTO> findAllHistories(Long customerId) {
        String uri = "/history/" + customerId + "/last-year";
        List<HistoryResponseDTO> histories = webClientUtil.getList(uri, HistoryResponseDTO.class);
        return histories;
    }
}
