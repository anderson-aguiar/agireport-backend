package analytic_service.controllers;

import analytic_service.dto.AnalyticResponseCompletedDTO;
import analytic_service.dto.AnalyticResponseDefaultDTO;
import analytic_service.services.AnalyticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
