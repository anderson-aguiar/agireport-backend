package analytic_service.services;

import analytic_service.dto.AnalyticResponseDTO;
import analytic_service.dto.CustomerResponseDTO;
import analytic_service.dto.HistoryResponseDTO;
import analytic_service.mapper.AnalyticMapper;
import analytic_service.model.Analytic;
import analytic_service.repositories.AnalyticRepository;
import analytic_service.utils.GenerateVector;
import analytic_service.utils.WebClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AnalyticMapper analyticMapper;

    public AnalyticService(
            AnalyticRepository analyticRepository,
            ScoreService scoreService,
            WebClientUtil webClientUtil,
            GenerateVector generateVector, AnalyticMapper analyticMapper) {
        this.analyticRepository = analyticRepository;
        this.scoreService = scoreService;
        this.webClientUtil = webClientUtil;
        this.generateVector = generateVector;
        this.analyticMapper = analyticMapper;
        log.info("Serviços injetados com sucesso");
    }

    @Transactional
    public AnalyticResponseDTO save(Long customerId) {

        List<HistoryResponseDTO> histories = findHistories(customerId);
        CustomerResponseDTO customer = findCustomer(customerId);
        int score = 0;
        // Verifica se o cliente tem menos de 30 dias ou não possui histórico
        LocalDate onCreateDate = customer.getOnCreate().toLocalDate();
        if (onCreateDate.isAfter(LocalDate.now().minusDays(30)) || histories.isEmpty()) {
            log.info("Cliente novo ou sem histórico. Score inicial = 300");
            log.info("Dados do customer: " + customer);
            log.info("Historico " + histories);

            score = 300;
        }

        double[] data = generateVector.generateData(histories, customer);
        log.info("Dados do vetor gerado: " + Arrays.toString(data));
        log.info("Dados do customer: " + customer);

        score = scoreService.calc(data);
        String typeOfRisk = getTypeOfRisk(score);
        Analytic entity = new Analytic(customerId, score, typeOfRisk);
        analyticRepository.save(entity);

        return analyticMapper.toDto(entity);
    }

    private String getTypeOfRisk(int score) {
        String typeOfRisk = "";
        if(score < 399){
            typeOfRisk = "ALTO RISCO";
        } else if (score < 699) {
            typeOfRisk = "MEDIO RISCO";
        }else {
            typeOfRisk = "BAIXO RISCO";
        }
        return typeOfRisk;
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




