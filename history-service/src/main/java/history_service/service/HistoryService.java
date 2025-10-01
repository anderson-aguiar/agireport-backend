package history_service.service;

import history_service.dtos.HistoryRequestDTO;
import history_service.dtos.HistoryResponseDTO;
import history_service.mappers.HistoryMapper;
import history_service.model.History;
import history_service.repository.HistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryMapper historyMapper;

    @Transactional
    public HistoryResponseDTO save(HistoryRequestDTO requestDTO) {

        History history = historyMapper.toEntity(requestDTO);
        historyRepository.save(history);

        return historyMapper.toDto(history);
    }

    @Transactional(readOnly = true)
    public HistoryResponseDTO findById(Long id) {
        History history = historyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Histórico não encontrado"));

        return historyMapper.toDto(history);
    }

    @Transactional(readOnly = true)
    public List<HistoryResponseDTO> findAllLastYearByCustomerId(Long customerId) {
        //Pega da data atual, desconta 1 ano e inicia com o horário de 0h
        LocalDateTime startDateMidNigth = LocalDateTime.now().minusYears(1).toLocalDate().atStartOfDay();
        List<HistoryResponseDTO> histories =
                historyRepository.findAllLastYearByCustomerId(customerId, startDateMidNigth)
                        .stream().map(historyMapper::toDto).toList();
        return histories;
    }

    @Transactional(readOnly = true)
    public Page<HistoryResponseDTO> findAllByCustomerId(Long customerId, Pageable pageable) {
        Page<HistoryResponseDTO> histories = historyRepository.findAllByCustomerId(customerId, pageable)
                .map(historyMapper::toDto);

        return histories;
    }


}
