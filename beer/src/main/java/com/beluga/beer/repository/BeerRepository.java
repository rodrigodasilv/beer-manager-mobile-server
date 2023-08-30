package com.beluga.beer.repository;

import com.beluga.beer.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long> {
    List<Beer> findByNameContainingIgnoreCaseAndPriceGreaterThanEqual(String name, Double minPrice);
    List<Beer> findByNameContainingIgnoreCase(String name);
    List<Beer> findByPriceGreaterThanEqual(Double minPrice);
}
