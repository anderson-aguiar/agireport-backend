package auth_service.controllers;

import auth_service.dtos.ApiKeyValidationDTO;
import auth_service.services.ApiKeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateKey(@RequestHeader("X-API-NAME") String apiName,
                                              @RequestHeader("X-API-KEY") String apiKey) {
        ApiKeyValidationDTO result = apiKeyService.validate(apiName, apiKey);

        if (result.isValid()) {
            return ResponseEntity.ok("Valid key");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Key invalid: " + result.getReason());
        }
    }
}
