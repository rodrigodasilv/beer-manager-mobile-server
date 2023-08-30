package com.beluga.beer.service;

import com.beluga.beer.model.Beer;
import com.beluga.beer.repository.BeerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private final BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    public Beer getBeerById(Long id) {
        Optional<Beer> beer = beerRepository.findById(id);

        if (beer.isPresent()){
            return beer.get();
        } else {
            throw new IllegalArgumentException("Cerveja não encontrada com ID: " + id);
        }
    }

    public Beer createBeer(String name, String description, double alcoholContent, String type, String origin,
                           String brewery, double price, boolean available) {

        Beer beer = new Beer(name, description, alcoholContent, type, origin, brewery, price, available);

        return beerRepository.save(beer);
    }

    public Beer updateBeer(Long id, String name, String description, double alcoholContent, String type, String origin,
                           String brewery, double price, boolean available) {
        Optional<Beer> existingBeer = beerRepository.findById(id);

        if (existingBeer.isPresent()) {
            Beer updatedBeer = new Beer(name, description, alcoholContent, type, origin, brewery, price, available);
            updatedBeer.setId(id);
            return beerRepository.save(updatedBeer);
        } else {
            throw new IllegalArgumentException("Cerveja não encontrada com ID: " + id);
        }
    }

    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }

    public List<Beer> searchBeers(String name, Double minPrice) {
        if (name == null && minPrice == null) {
            return beerRepository.findAll();
        }

        if (name != null && minPrice != null) {
            return beerRepository.findByNameContainingIgnoreCaseAndPriceGreaterThanEqual(name, minPrice);
        }

        if (name != null) {
            return beerRepository.findByNameContainingIgnoreCase(name);
        }

        if (minPrice != null) {
            return beerRepository.findByPriceGreaterThanEqual(minPrice);
        }

        return List.of();
    }
}
