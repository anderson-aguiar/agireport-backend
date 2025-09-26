package history_service.controller;

import history_service.dto.HistoryRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @PostMapping
    public ResponseEntity<HistoryRequestDTO> create(@RequestBody HistoryRequestDTO requestDTO){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryRequestDTO> findById(@PathVariable Long id){
        return null;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<HistoryRequestDTO>> findAllByCustomerId(@PathVariable Long customerId){

        return null;
    }
    @GetMapping("/{customerId}/last-year")
    public ResponseEntity<List<HistoryRequestDTO>> findAllLastYearByCustomerId(@PathVariable Long customerId){

        return null;
    }

}
