package entities.carbon;

import java.util.ArrayList;
import java.util.List;

public class CarbonEstimateRequest {
    public final String type = "flight";
    public final int passengers;
    public final List<CarbonEstimateLeg> legs;

    public CarbonEstimateRequest(int passengers, List<String> path) {
        this.passengers = passengers;
        this.legs = new ArrayList<>();
        for (int i = 1; i < path.size(); i++) {
            legs.add(new CarbonEstimateLeg(path.get(i - 1), path.get(i)));
        }
    }

    public CarbonEstimateRequest(int passengers, String from, String to) {
        this(passengers, List.of(from, to));
    }

}
