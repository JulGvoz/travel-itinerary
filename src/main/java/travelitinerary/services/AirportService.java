package travelitinerary.services;

import entities.airports.Airport;
import org.springframework.stereotype.Service;
import travelitinerary.repositories.AirportRepository;

import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport getByIATA(String iata) {
        return airportRepository.getById(iata);
    }

    public Airport getByCityName(String cityName) {
        return airportRepository.findByCityName(cityName);
    }

    public void clear() {
        airportRepository.deleteAll();
    }

    public List<Airport> save(List<Airport> airports) {
        return airportRepository.saveAll(airports);
    }

    public int count() {
        return (int) airportRepository.count();
    }
}
