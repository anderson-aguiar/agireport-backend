package analytic_service.services;

import analytic_service.dto.AnalyticResponseDTO;
import analytic_service.dto.CustomerResponseDTO;
import analytic_service.dto.HistoryResponseDTO;
import analytic_service.exceptions.ServiceNotFoundException;
import analytic_service.mapper.AnalyticMapper;
import analytic_service.model.Analytic;
import analytic_service.repositories.AnalyticRepository;
import analytic_service.utils.GenerateVector;
import analytic_service.utils.WebClientUtil;
import jakarta.persistence.EntityNotFoundException;
import org.nd4j.autodiff.listeners.records.History;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        }else {
            double[] data = generateVector.generateData(histories, customer);
            log.info("Dados do vetor gerado: " + Arrays.toString(data));
            log.info("Dados do customer: " + customer);

            score = scoreService.calc(data);

        }

        LocalDateTime startDate = LocalDate.now().minusDays(10).atStartOfDay();
        //Faz a busca no banco para ver se esse cliente já tem alguma análise nos últimos 10 dias
        //Se tiver o retorno será essa análise, não faz uma nova persistência
        Optional<Analytic> optionalAnalytic = analyticRepository.findByCustomerIdLastTenDays(customerId, startDate);
        if(optionalAnalytic.isPresent()){
            return analyticMapper.toDto(optionalAnalytic.get(), onCreateDate);
        }

        String typeOfRisk = getTypeOfRisk(score);
        Analytic entity = new Analytic(customerId, score, typeOfRisk);
        analyticRepository.save(entity);

        return analyticMapper.toDto(entity, onCreateDate);
    }

    private String getTypeOfRisk(int score) {
        String typeOfRisk = "";
        if (score < 350) {
            typeOfRisk = "ALTO RISCO";
        } else if (score < 699) {
            typeOfRisk = "MEDIO RISCO";
        } else {
            typeOfRisk = "BAIXO RISCO";
        }
        return typeOfRisk;
    }

    private List<HistoryResponseDTO> findHistories(Long customerId) {
        try{
            String uri = "/history/" + customerId + "/last-year";
            return webClientUtil.getList(uri, HistoryResponseDTO.class);
        }catch (WebClientResponseException.InternalServerError ex){
            throw new ServiceNotFoundException("History-Service indiponivel");
        }
    }

    private CustomerResponseDTO findCustomer(Long customerId) {
        try{
            String uri = "/customers/" + customerId;
            return webClientUtil.get(uri, CustomerResponseDTO.class);


        } catch (WebClientResponseException.NotFound ex) {
            throw new EntityNotFoundException("Cliente " + customerId + " não encontrado", ex);

        } catch (WebClientResponseException.InternalServerError | WebClientRequestException ex) {
            throw new ServiceNotFoundException("Customer-Service indisponível");
        }
    }
}




