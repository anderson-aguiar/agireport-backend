package auth_service.dtos;

public class ApiKeyValidationDTO {
    private boolean valid;
    private String reason; // inativa, inválida, etc

    public ApiKeyValidationDTO(boolean valid, String reason) {
        this.valid = valid;
        this.reason = reason;
    }

    public boolean isValid() {
        return valid;
    }

    public String getReason() {
        return reason;
    }
}
