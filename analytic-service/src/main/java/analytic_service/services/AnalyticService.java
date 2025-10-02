package analytic_service.services;

import analytic_service.dto.CustomerResponseDTO;
import analytic_service.dto.HistoryResponseDTO;
import analytic_service.repositories.AnalyticRepository;
import analytic_service.utils.GenerateVector;
import analytic_service.utils.WebClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class AnalyticService {
    private static final Logger log = LoggerFactory.getLogger(AnalyticService.class);

    private final AnalyticRepository analyticRepository;
    private final ScoreService scoreService;
    private final WebClientUtil webClientUtil;
    private final GenerateVector generateVector;

    public AnalyticService(
            AnalyticRepository analyticRepository,
            ScoreService scoreService,
            WebClientUtil webClientUtil,
            GenerateVector generateVector) {
        this.analyticRepository = analyticRepository;
        this.scoreService = scoreService;
        this.webClientUtil = webClientUtil;
        this.generateVector = generateVector;
        log.info("Serviços injetados com sucesso");
    }

    public Integer save(Long customerId) {

        List<HistoryResponseDTO> histories = findHistories(customerId);
        CustomerResponseDTO customer = findCustomer(customerId);

        // Verifica se o cliente tem menos de 30 dias ou não possui histórico
        LocalDate onCreateDate = customer.getOnCreate().toLocalDate();
        if (onCreateDate.isAfter(LocalDate.now().minusDays(30)) || histories.isEmpty()) {
            log.info("Cliente novo ou sem histórico. Score inicial = 300");
            log.info("Dados do customer: " + customer);
            log.info("Historico " + histories);

            return 300;
        }


        double[] data = generateVector.generateData(histories, customer);
        log.info("Dados do vetor gerado: " + Arrays.toString(data));
        log.info("Dados do customer: " + customer);

        return scoreService.calc(data);
    }

    private List<HistoryResponseDTO> findHistories(Long customerId) {
        String uri = "/history/" + customerId + "/last-year";
        return webClientUtil.getList(uri, HistoryResponseDTO.class);
    }

    private CustomerResponseDTO findCustomer(Long customerId) {
        String uri = "/customers/" + customerId;
        return webClientUtil.get(uri, CustomerResponseDTO.class);
    }
}




