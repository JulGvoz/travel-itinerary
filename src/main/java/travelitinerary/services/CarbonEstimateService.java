package travelitinerary.services;

import entities.carbon.CarbonEstimateRequest;
import entities.carbon.CarbonEstimateResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class CarbonEstimateService {
    private final WebClient webClient;

    public CarbonEstimateService(APIService apiService) {
        this.webClient = WebClient
                .builder()
                .baseUrl("https://www.carboninterface.com")
                .defaultHeader("Authorization", "Bearer " + apiService.getCarbonEstimate())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private CarbonEstimateResponse get(CarbonEstimateRequest request) {
        return webClient.post()
                .uri("/api/v1/estimates")
                .body(request, CarbonEstimateRequest.class)
                .retrieve()
                .bodyToMono(CarbonEstimateResponse.class)
                .block(Duration.ofSeconds(5));
    }

    public CarbonEstimateResponse get(int passengers, String from, String to) {
        return this.get(new CarbonEstimateRequest(passengers, from, to));
    }

    public CarbonEstimateResponse get(String from, String to) {
        return this.get(1, from, to);
    }

}
