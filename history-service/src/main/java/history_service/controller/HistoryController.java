package history_service.controller;

import history_service.dtos.HistoryRequestDTO;
import history_service.dtos.HistoryResponseDTO;
import history_service.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@Tag(name = "History", description = "Operações relacionadas ao histórico do cliente.")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Operation(summary = "Criar novo histórico",
            description = "Cadastra um novo historico no sistema com base nos dados enviados no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HistoryRequestDTO.class)))
    })
    @PostMapping
    public ResponseEntity<HistoryResponseDTO> create(@RequestBody HistoryRequestDTO requestDTO) {
        HistoryResponseDTO response = historyService.save(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Buscar histórico por ID",
            description = "Retorna os detalhes de um histórico específico a partir do seu identificador único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HistoryResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HistoryResponseDTO> findById(@PathVariable Long id) {
        HistoryResponseDTO response = historyService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Buscar históricos paginados",
            description = "Retorna uma página de históricos do cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de históricos",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = HistoryResponseDTO.class)))),
    })
    @GetMapping("/all/{customerId}")
    public ResponseEntity<Page<HistoryResponseDTO>> findAllByCustomerId(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable Long customerId,
            @ParameterObject
            Pageable pageable) {
        Page<HistoryResponseDTO> histories = historyService.findAllByCustomerId(customerId, pageable);
        return ResponseEntity.ok(histories);
    }


    @Operation(
            summary = "Buscar uma lista de históricos por ID",
            description = "Retorna uma lista que contem os detalhes de um histórico específico a partir do seu identificador único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = HistoryResponseDTO.class)))),
    })
    @GetMapping("/{customerId}/last-year")
    public ResponseEntity<List<HistoryResponseDTO>> findAllLastYearByCustomerId(@PathVariable Long customerId) {
        List<HistoryResponseDTO> histories = historyService.findAllLastYearByCustomerId(customerId);
        return ResponseEntity.ok(histories);
    }

}
