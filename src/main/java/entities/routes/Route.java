package entities.routes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(
        indexes = @Index(columnList = "departureAirport, destinationAirport")
)
public class Route {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("source_airport")
    private String departureAirport;
    @JsonProperty("destination_airport")
    private String destinationAirport;
}
