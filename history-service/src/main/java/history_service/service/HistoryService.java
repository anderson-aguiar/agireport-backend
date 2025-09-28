package history_service.service;

import history_service.dtos.HistoryRequestDTO;
import history_service.dtos.HistoryResponseDTO;
import history_service.mappers.HistoryMapper;
import history_service.model.History;
import history_service.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
