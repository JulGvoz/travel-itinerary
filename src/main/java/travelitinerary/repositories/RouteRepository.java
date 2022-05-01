package travelitinerary.repositories;

import entities.routes.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByDepartureAirport(String departureAirport);
    List<Route> findByDestinationAirport(String destinationAirport);
    List<Route> findByDepartureAirportAndDestinationAirport(String departureAirport, String destinationAirport);
}
