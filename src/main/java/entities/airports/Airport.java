package entities.airports;

import javax.persistence.*;

@Entity
@Table(
        indexes = @Index(columnList = "cityName")
)
public class Airport {
    @Id
    @Column(name = "iata", nullable = false, length = 3)
    private String iata;

    private String cityName;
    private String airportName;

    private Double latitude;
    private Double longitude;

}
