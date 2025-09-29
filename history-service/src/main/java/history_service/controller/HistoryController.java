package history_service.controller;

import history_service.dtos.HistoryRequestDTO;
import history_service.dtos.HistoryResponseDTO;
import history_service.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping
    public ResponseEntity<HistoryResponseDTO> create(@RequestBody HistoryRequestDTO requestDTO){
        HistoryResponseDTO response = historyService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryRequestDTO> findById(@PathVariable Long id){
        return null;
    }

    @GetMapping("/all/{customerId}")
    public ResponseEntity<List<HistoryRequestDTO>> findAllByCustomerId(@PathVariable Long customerId){

        return null;
    }
    @GetMapping("/{customerId}/last-year")
    public ResponseEntity<List<HistoryResponseDTO>> findAllLastYearByCustomerId(@PathVariable Long customerId){
        List<HistoryResponseDTO> histories = historyService.findAllLastYearByCustomerId(customerId);
        return ResponseEntity.ok(histories);
    }

}
