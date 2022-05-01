package travelitinerary;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import entities.airports.Airport;
import entities.countries.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import travelitinerary.services.APIService;
import travelitinerary.services.AirportService;
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
				System.out.println("No api key for Carbon Estimates!");
			} else {
				apiService.setCarbonEstimate(args[0]);
				System.out.println("Set API key of Carbon Estimate to " + args[0]);
			}
		};
	}

	@Bean
	CommandLineRunner apiKeyTravelPayouts(APIService apiService) {
		return args -> {
			if (args == null || args.length < 2) {
				System.out.println("No api key for Travelpayouts");
			} else {
				apiService.setTravelPayouts(args[1]);
				System.out.println("Set API key of Travel Payouts to " + args[1]);
			}
		};
	}

	/*
	Airport ID 	Unique OpenFlights identifier for this airport.
Name 	Name of airport. May or may not contain the City name.
City 	Main city served by airport. May be spelled differently from Name.
Country 	Country or territory where airport is located. See Countries to cross-reference to ISO 3166-1 codes.
IATA 	3-letter IATA code. Null if not assigned/unknown.
ICAO 	4-letter ICAO code.
Null if not assigned.
Latitude 	Decimal degrees, usually to six significant digits. Negative is South, positive is North.
Longitude 	Decimal degrees, usually to six significant digits. Negative is West, positive is East.
Altitude 	In feet.
Timezone 	Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.
DST 	Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown). See also: Help: Time
Tz database time zone 	Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
Type 	Type of the airport. Value "airport" for air terminals, "station" for train stations, "port" for ferry terminals and "unknown" if not known. In airports.csv, only type=airport is included.
Source 	Source of this data. "OurAirports", "Legacy" , "User"
	 */
	@Bean
	CommandLineRunner loadAirports(AirportService airportService) {
		return args -> {
			if (airportService.count() < 10) {
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

			TypeReference<List<Airport>> typeReference = new TypeReference<>() {};
			try (InputStream inputStream =
						 TypeReference.class.getResourceAsStream("/data/airports_clean.json")) {
				List<Airport> airports = mapper.readValue(inputStream, typeReference);
				airportService.clear();
				airportService.save(airports);
				System.out.println("Saved " + airports.size() + " airports!");
			} catch (IOException e) {
				System.err.println("Unable to save airports: " + e.getMessage());
			}
		};
	}
}
