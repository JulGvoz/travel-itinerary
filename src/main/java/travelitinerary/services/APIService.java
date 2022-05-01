package travelitinerary.services;

import org.springframework.stereotype.Service;

@Service
public class APIService {

    private String carbonEstimate = "";
    private String travelPayouts = "";

    public String getCarbonEstimate() {
        return carbonEstimate;
    }

    public void setCarbonEstimate(String carbonEstimate) {
        this.carbonEstimate = carbonEstimate;
    }

    public void setTravelPayouts(String travelPayouts) {
        this.travelPayouts = travelPayouts;
    }

    public String getTravelPayouts() {
        return travelPayouts;
    }
}
