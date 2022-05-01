package entities.entries;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record EntryResponse(String city, String country, Date startDate,
                            Date endDate, double price,
                            @JsonProperty("cityImage") String cityImageURL,
                            List<String> sights) {
}
