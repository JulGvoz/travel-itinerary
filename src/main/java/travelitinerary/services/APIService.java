package travelitinerary.services;

import org.springframework.stereotype.Service;

@Service
public class APIService {

    private String carbonEstimate = "";

    public String getCarbonEstimate() {
        return carbonEstimate;
    }

    public void setCarbonEstimate(String carbonEstimate) {
        this.carbonEstimate = carbonEstimate;
    }
}
