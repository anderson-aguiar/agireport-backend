package customer_service.repositories;

import customer_service.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM tb_customer WHERE id = :id AND deleted_at IS NULL", nativeQuery = true)
    Optional<Customer> findByIdNotDeleted(@Param("id") Long id);

    @Query(value = "SELECT * FROM tb_customer WHERE deleted_at IS NULL",
            countQuery = "SELECT COUNT(*) FROM tb_customer WHERE deleted_at IS NULL", nativeQuery = true)
    Page<Customer> findAllNotDeleted(Pageable pageable);



}


