package history_service.repository;

import history_service.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByCustomerId(Long id);

    @Query(value = "SELECT * FROM tb_history tb WHERE tb.customer_id = :customerId AND tb.on_create >= :startDate",
            nativeQuery = true)
    List<History> findAllLastYearByCustomerId(
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDateTime startDate);
}
