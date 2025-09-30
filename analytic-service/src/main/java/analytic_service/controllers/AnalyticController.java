package analytic_service.controllers;

import analytic_service.dto.HistoryResponseDTO;
import analytic_service.services.AnalyticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytic")
public class AnalyticController {

    private final AnalyticService analyticService;

    public AnalyticController(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    //TESTE DE REQUISIÇÃO AO MICROSERVIÇO HISTORY
    @GetMapping("/{customerId}")
    public ResponseEntity<List<HistoryResponseDTO>> findAll(@PathVariable Long customerId){
        List<HistoryResponseDTO> response = analyticService.findAllHistories(customerId);

        return ResponseEntity.ok(response);
    }
}
