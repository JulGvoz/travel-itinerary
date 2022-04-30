package travelitinerary;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
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
	CommandLineRunner runner(CountryService countryService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			TypeReference<List<Country>> typeReference = new TypeReference<>() {};
			// 			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			try (InputStream inputStream =
						 TypeReference.class.getResourceAsStream("/data/countries_cities.json")) {
				List<Country> countries = mapper.readValue(inputStream, typeReference);
				countryService.clear();
				var saved = countryService.save(countries);
				System.out.println("Saved " + countries.size() + " countries!");
			} catch (IOException e) {
				System.err.println("Unable to save countries: " + e.getMessage());
			}
		};
	}
}
