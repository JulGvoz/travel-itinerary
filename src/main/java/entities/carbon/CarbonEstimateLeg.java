package entities.carbon;

import com.fasterxml.jackson.annotation.JsonProperty;

record CarbonEstimateLeg(
        @JsonProperty("departure_airport") String departureAirport,
        @JsonProperty("destinationAirport") String destinationAirport) {
}
