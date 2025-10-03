package analytic_service.repositories;

import analytic_service.model.Analytic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnalyticRepository extends JpaRepository<Analytic, Long> {

    @Query(value = "SELECT * FROM tb_analytic WHERE  customer_id = :customerId " +
            "AND on_create >= :startDate ORDER BY on_create DESC LIMIT 1", nativeQuery = true)
    Optional<Analytic> findByCustomerIdLastTenDays(
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDateTime startDate);

    @Query(value = "SELECT * FROM tb_analytic WHERE  customer_id = :customerId " +
            "AND on_create >= :startDate AND status = 'PROCESSANDO' ORDER BY on_create DESC LIMIT 1", nativeQuery = true)
    Optional<Analytic> findByCustomerIdLastTenDaysAndStatusProcess(
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDateTime startDate);

    List<Analytic> findByStatus(String status);
}
