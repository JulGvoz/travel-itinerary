package entities.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @Id
    @Column(nullable = false)
    private String name;

    private String region;

    @OneToMany(cascade = CascadeType.ALL)
    private List<City> cities;

    private Double latitude;
    private Double longitude;

    public Country() {}

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public List<City> getCities() {
        return cities;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
