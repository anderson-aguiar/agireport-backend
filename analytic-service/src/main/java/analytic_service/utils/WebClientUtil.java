package analytic_service.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WebClientUtil {

    private final WebClient webClient;

    // Injetando o WebClient com a baseUrl
    public WebClientUtil(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8084")
                .build();
    }

    // GET genérico que retorna o tipo informado e apenas 1 objeto
    public <T> T get(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
    //Get generico que retorna vários objetos
    public <T> List<T> getList(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(responseType)
                .collectList()
                .block();
    }


}
