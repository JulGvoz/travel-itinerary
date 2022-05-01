package entities.carbon;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CarbonEstimateResponse(Data data
        ) {
    public record Attributes(int passengers, List<CarbonEstimateLeg> legs) {}
    public record Data(String id, String type,
                       entities.carbon.CarbonEstimateResponse.Attributes attributes,
                       @JsonProperty("carbon_kg") int carbonKg,
                       @JsonProperty("distance_value") double distanceKm) {}
}
