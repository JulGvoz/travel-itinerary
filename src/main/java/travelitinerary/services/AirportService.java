package travelitinerary.services;

import entities.airports.Airport;
import org.springframework.stereotype.Service;
import travelitinerary.repositories.AirportRepository;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport getByIATA(String iata) {
        return airportRepository.getById(iata);
    }
}
