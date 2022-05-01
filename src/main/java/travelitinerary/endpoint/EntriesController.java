package travelitinerary.endpoint;

import entities.countries.Country;
import entities.entries.EntryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travelitinerary.repositories.CountryRepository;

import java.util.*;

@RestController
@RequestMapping("/api/entries")
public class EntriesController {
    @Autowired
    private final CountryRepository countryRepository;

    public EntriesController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("")
    public EntryResponse get(
            @RequestParam(value = "starting_place", required = false) String from,
            @RequestParam(value = "destination", required = false) String to,
            @RequestParam(value = "number_of_people", required = false) Integer peopleCount,
            @RequestParam(value = "start_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(value = "end_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {
        var rng = new Random();
        var price = rng.nextInt(1600) + 200;
        var country = new Country();
        while (from == null) {
            var countries = countryRepository.findAll();
            Collections.shuffle(countries);
            country = countries.get(0);
            var cities = country.getCities();
            Collections.shuffle(cities);
            if (cities.size() > 0)
                from = cities.get(0).getName();
        }
        if (to == null) {
            while (to == null) {
                var countries = countryRepository.findAll();
                Collections.shuffle(countries);
                country = countries.get(0);
                var cities = country.getCities();
                Collections.shuffle(cities);
                if (cities.size() > 0)
                    to = cities.get(0).getName();

            }
        } else {
            var countries = countryRepository.findAll();
            String finalTo = to;
            var cntry = countries
                    .stream()
                    .filter(
                            country1 ->
                                    country1
                                            .getCities()
                                            .stream()
                                            .anyMatch(city -> Objects.equals(city.getName(), finalTo)))
                    .findAny();
            if (cntry.isPresent()) {
                country = cntry.get();
            }
        }
        while (startDate == null) {
            startDate = new Date(new Date().getTime() + 24L*60*60*1000*(rng.nextInt() % 30));
        }
        while (endDate == null) {
            endDate = new Date(startDate.getTime() + 24L*60*60*1000*(rng.nextInt() % 30));
        }
        while (peopleCount == null || peopleCount < 1) {
            peopleCount = 1;
        }
        var sights = new ArrayList<>(List.of("Museums", "Water parks", "Beach", "Sea", "Ocean", "Nightlife", "Spa", "Natural wonders"));
        Collections.shuffle(sights);
        return new EntryResponse(
                to, country.getName(),
                startDate, endDate,
                price,
                "https://random.imagecdn.app/500/150",
                sights.subList(0, rng.nextInt(3) + 1)
        );
    }

}
