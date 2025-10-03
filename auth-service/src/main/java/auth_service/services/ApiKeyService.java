package auth_service.services;

import auth_service.dtos.ApiKeyValidationDTO;
import auth_service.model.ApiKey;
import auth_service.repositories.ApiKeyRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ApiKeyService {
    private final ApiKeyRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ApiKeyService(ApiKeyRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ApiKeyValidationDTO validate(String systemName, String rawKey) {
        Optional<ApiKey> optionalKey = repository.findBySystemName(systemName);

        if (optionalKey.isEmpty()) {
            return new ApiKeyValidationDTO(false, "not_found");
        }

        ApiKey storedKey = optionalKey.get();

        if (!storedKey.isActive()) {
            return new ApiKeyValidationDTO(false, "inactive");
        }

        boolean matches = passwordEncoder.matches(rawKey, storedKey.getApiKeyHash());
        if (!matches) {
            return new ApiKeyValidationDTO(false, "invalid_key");
        }

        return new ApiKeyValidationDTO(true, "ok");
    }

    //usado para criar o hash da key
    public String encodeKey(String key) {
        return passwordEncoder.encode(key);
    }

}
