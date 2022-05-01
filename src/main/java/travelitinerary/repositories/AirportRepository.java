package travelitinerary.repositories;

import entities.airports.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
    Airport findByCityName(String cityName);
}
