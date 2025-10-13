package analytic_service.controllers;

import analytic_service.dto.AnalyticResponseCompletedDTO;
import analytic_service.dto.AnalyticResponseDefaultDTO;
import analytic_service.model.Analytic;
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

    @PostMapping("/{customerId}")
    public ResponseEntity<AnalyticResponseDefaultDTO> create(@PathVariable Long customerId){
        AnalyticResponseDefaultDTO response = analyticService.save(customerId);

        if (response instanceof AnalyticResponseCompletedDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<?>> findByCustomer(@PathVariable Long customerId) {
        List<?> response = analyticService.findByCustomerId(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
