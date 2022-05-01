package travelitinerary.services;

import entities.routes.Route;
import org.springframework.stereotype.Service;
import travelitinerary.repositories.RouteRepository;

import java.util.List;

@Service
public class RouteService implements LoadableService<Route> {
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route get(String from, String to) {
        return routeRepository.findByDepartureAirportAndDestinationAirport(from, to).get(0);
    }

    public Route getByDeparture(String from) {
        return routeRepository.findByDepartureAirport(from).get(0);
    }

    public Route getByDestination(String to) {
        return routeRepository.findByDestinationAirport(to).get(0);
    }

    public void clear() {
        routeRepository.deleteAll();
    }

    public List<Route> save(List<Route> routes) {
        return routeRepository.saveAll(routes);
    }

    public int count() {
        return (int) routeRepository.count();
    }
}
