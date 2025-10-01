package analytic_service.services;

import analytic_service.dto.AnalyticResponseDTO;
import analytic_service.dto.CustomerResponseDTO;
import analytic_service.dto.HistoryResponseDTO;
import analytic_service.repositories.AnalyticRepository;
import analytic_service.utils.GenerateVector;
import analytic_service.utils.WebClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticService {

    private List<HistoryResponseDTO> histories = new ArrayList<>();
    private CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
    private double[] data;


    private final AnalyticRepository analyticRepository;

    private final WebClientUtil webClientUtil;
    private final GenerateVector generateVector;

    public AnalyticService(AnalyticRepository analyticRepository, WebClientUtil webClientUtil, GenerateVector generateVector) {
        this.analyticRepository = analyticRepository;
        this.webClientUtil = webClientUtil;
        this.generateVector = generateVector;
    }


    public AnalyticResponseDTO save(Long customerId) {
        return null;
    }

    public void findAllHistoriesAndCustomer(Long customerId) {
        String uri = "/history/" + customerId + "/last-year";
        histories = webClientUtil.getList(uri, HistoryResponseDTO.class);
        String uri2 = "/customers/" + customerId;
        customerResponseDTO = webClientUtil.get(uri2, CustomerResponseDTO.class);
    }

    public void generateVector() {
        data = generateVector.generateData(histories, customerResponseDTO);
    }
}




