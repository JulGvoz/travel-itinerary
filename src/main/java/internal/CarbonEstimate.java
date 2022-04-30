package internal;

import org.springframework.web.reactive.function.client.WebClient;
import travelitinerary.services.APIService;

public class CarbonEstimate {
    private final WebClient webClient;

    public CarbonEstimate(APIService apiService) {
        this.webClient = WebClient
                .builder()
                .defaultHeader("Authorization", "Bearer " + apiService.getCarbonEstimate())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

}
