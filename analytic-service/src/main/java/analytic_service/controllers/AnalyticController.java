package analytic_service.controllers;

import analytic_service.dto.AnalyticResponseDTO;
import analytic_service.dto.HistoryResponseDTO;
import analytic_service.services.AnalyticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytic")
public class AnalyticController {

    private final AnalyticService analyticService;

    public AnalyticController(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    //TESTE DE REQUISIÇÃO AO MICROSERVIÇO HISTORY


    @PostMapping("/{customerId}")
    public ResponseEntity<AnalyticResponseDTO> create(@PathVariable Long customerId){
        AnalyticResponseDTO response = analyticService.save(customerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
