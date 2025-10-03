package auth_service.repositories;

import auth_service.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findBySystemNameAndActiveTrue(String systemName);

    Optional<ApiKey> findBySystemName(String systemName);
}