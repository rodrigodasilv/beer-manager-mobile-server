package com.beluga.beer.controller;

import com.beluga.beer.dto.BeerDTO;
import com.beluga.beer.dto.CreateBeerDTO;
import com.beluga.beer.model.Beer;
import com.beluga.beer.service.BeerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/beer")
public class BeerController {

    private final BeerService beerService;
    private final ModelMapper modelMapper;

    public BeerController(BeerService beerService, ModelMapper modelMapper) {
        this.beerService = beerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public List<BeerDTO> findAll(){
        List<Beer> allBeers = beerService.getAllBeers();

        return allBeers.stream()
                .map(beer -> modelMapper.map(beer, BeerDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BeerDTO findById(@PathVariable("id") Long id) {
        Beer beerById = beerService.getBeerById(id);
        return modelMapper.map(beerById, BeerDTO.class);
    }

    @PostMapping("/")
    public BeerDTO addNewBeer(@RequestBody CreateBeerDTO createBeerDTO){

        Beer beer = beerService.createBeer(createBeerDTO.getName(), createBeerDTO.getDescription(),
                createBeerDTO.getAlcoholContent(), createBeerDTO.getType(), createBeerDTO.getOrigin(),
                createBeerDTO.getBrewery(), createBeerDTO.getPrice(), createBeerDTO.isAvailable());

        return modelMapper.map(beer, BeerDTO.class);
    }

    @PostMapping("/{id}")
    public BeerDTO updateBeer(@PathVariable("id") Long id,
                              @RequestBody CreateBeerDTO createBeerDTO){

        Beer beer = beerService.updateBeer(id, createBeerDTO.getName(), createBeerDTO.getDescription(),
                createBeerDTO.getAlcoholContent(), createBeerDTO.getType(), createBeerDTO.getOrigin(),
                createBeerDTO.getBrewery(), createBeerDTO.getPrice(), createBeerDTO.isAvailable());

        return modelMapper.map(beer, BeerDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable("id") Long id){
        beerService.deleteBeer(id);
    }

    @GetMapping("/search")
    public List<BeerDTO> searchBeers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice
    ) {
        List<Beer> filteredBeers = beerService.searchBeers(name, minPrice);

        return filteredBeers.stream()
                .map(beer -> modelMapper.map(beer, BeerDTO.class))
                .collect(Collectors.toList());
    }

}
