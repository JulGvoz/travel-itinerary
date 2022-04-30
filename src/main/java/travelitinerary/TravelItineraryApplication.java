package travelitinerary;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.countries.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import travelitinerary.services.APIService;
import travelitinerary.services.CountryService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = { "travelitinerary", "entities" })
public class TravelItineraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelItineraryApplication.class, args);
	}

	@Bean
	CommandLineRunner loadCountries(CountryService countryService) {
		return args -> {
			if (countryService.count() > 10) {
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			TypeReference<List<Country>> typeReference = new TypeReference<>() {};
			try (InputStream inputStream =
						 TypeReference.class.getResourceAsStream("/data/countries_cities.json")) {
				List<Country> countries = mapper.readValue(inputStream, typeReference);
				countryService.clear();
				countryService.save(countries);
				System.out.println("Saved " + countries.size() + " countries!");
			} catch (IOException e) {
				System.err.println("Unable to save countries: " + e.getMessage());
			}
		};
	}

	@Bean
	CommandLineRunner apiKeyCarbonEstimates(APIService apiService) {
		return args -> {
			if (args == null || args.length < 1) {
				System.out.println("No api key is loaded!");
			} else {
				apiService.setCarbonEstimate(args[0]);
				System.out.println("Set API key of Carbon Estimate to " + args[0]);
			}
		};
	}
}
