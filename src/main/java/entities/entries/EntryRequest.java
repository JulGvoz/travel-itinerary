package entities.entries;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public record EntryRequest(@JsonProperty("starting_place") String startingPlace, String destination,
                           @JsonProperty("number_of_people") int peopleCount,
                           @JsonProperty("start_date") Date startDate,
                           @JsonProperty("end_date") Date endDate) {
}
