package entities.carbon;

import java.util.ArrayList;
import java.util.List;

public class FlightEstimateRequest {
    public final String type = "flight";
    public final int passengers;
    public final List<Leg> legs;

    public FlightEstimateRequest(int passengers, List<String> path) {
        this.passengers = passengers;
        this.legs = new ArrayList<>();
        for (int i = 1; i < path.size(); i++) {
            legs.add(new Leg(path.get(i - 1), path.get(i)));
        }
    }

    private record Leg(String departure_airport, String destination_airport) {}
}
