package travelitinerary.services;

import entities.countries.Country;
import org.springframework.stereotype.Service;
import travelitinerary.repositories.CountryRepository;

import java.util.List;

@Service
public class CountryService implements LoadableService<Country> {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Iterable<Country> iterable() {
        return countryRepository.findAll();
    }

    public List<Country> save(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    public void clear() {
        countryRepository.deleteAll();
    }

    public int count() {
        return (int) countryRepository.count();
    }
}
