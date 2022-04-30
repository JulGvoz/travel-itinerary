package travelitinerary.services;

import entities.Country;
import org.springframework.stereotype.Service;
import travelitinerary.repositories.CountryRepository;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Iterable<Country> iterable() {
        return countryRepository.findAll();
    }

    public Iterable<Country> save(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    public void clear() {
        countryRepository.deleteAll();
    }
}
