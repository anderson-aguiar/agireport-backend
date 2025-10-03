package api_gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiKeyAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    // O WebClient é usado para fazer a chamada para o serviço de autenticação
    private final WebClient webClient;

    // O construtor injeta a URL do serviço de autenticação do application.yml
    public ApiKeyAuthGatewayFilterFactory(WebClient.Builder builder, @Value("${agireport.services.auth-url}") String authServiceUrl) {
        super(Object.class);
        this.webClient = builder.baseUrl(authServiceUrl).build();
    }

    // Este método define a lógica do filtro
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String apiName = headers.getFirst("X-API-NAME");
            String apiKey = headers.getFirst("X-API-KEY");

            // Se os cabeçalhos não existirem, a requisição é negada
            if (apiName == null || apiKey == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Faz a chamada para o serviço de autenticação para validar a chave
            return webClient.post()
                    .uri("/validate")
                    .header("X-API-NAME", apiName)
                    .header("X-API-KEY", apiKey)
                    .exchangeToMono(clientResponse -> {
                        if (clientResponse.statusCode().is2xxSuccessful()) {
                            // Chave válida → continua a requisição
                            return chain.filter(exchange);
                        } else {
                            // Chave inválida → cria JSON com código e mensagem
                            return clientResponse.bodyToMono(String.class)
                                    .flatMap(body -> {
                                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                        exchange.getResponse().getHeaders().add("Content-Type", "application/json");

                                        Map<String, Object> responseBody = new HashMap<>();
                                        responseBody.put("code", 401);
                                        responseBody.put("message", body); // corpo retornado pelo auth-service

                                        ObjectMapper mapper = new ObjectMapper();
                                        byte[] bytes;
                                        try {
                                            bytes = mapper.writeValueAsBytes(responseBody);
                                        } catch (JsonProcessingException e) {
                                            bytes = "{\"code\":401,\"message\":\"error processing body\"}".getBytes();
                                        }

                                        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                                        return exchange.getResponse().writeWith(Mono.just(buffer));
                                    });
                        }
                    });
        };
    }
}
