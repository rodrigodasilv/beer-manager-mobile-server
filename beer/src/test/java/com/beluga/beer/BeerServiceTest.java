package com.beluga.beer;

import com.beluga.beer.model.Beer;
import com.beluga.beer.repository.BeerRepository;
import com.beluga.beer.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    private BeerService beerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        beerService = new BeerService(beerRepository);
    }

    @Test
    public void testGetAllBeers() {
        List<Beer> expectedBeers = new ArrayList<>();
        expectedBeers.add(new Beer(1L,"Skol","Uma cerveja leve e refrescante",4.5,
                "Pilsen","Brasil","Ambev",3.5,true));
        expectedBeers.add(new Beer(2L,"Skol","Uma cerveja leve e refrescante",4.5,
                "Pilsen","Brasil","Ambev",3.5,true));

        when(beerRepository.findAll()).thenReturn(expectedBeers);

        List<Beer> actualBeers = beerService.getAllBeers();

        assertThat(actualBeers).isEqualTo(expectedBeers);
    }

    @Test
    public void testGetBeerByIdExisting() {
        Long beerId = 1L;
        Beer expectedBeer = new Beer(1L,"Skol","Uma cerveja leve e refrescante",4.5,
                "Pilsen","Brasil","Ambev",3.5,true);

        when(beerRepository.findById(beerId)).thenReturn(Optional.of(expectedBeer));

        Beer actualBeer = beerService.getBeerById(beerId);

        assertThat(actualBeer).isEqualTo(expectedBeer);
    }

    @Test
    public void testGetBeerByIdNonExisting() {
        Long beerId = 76L;

        when(beerRepository.findById(beerId)).thenReturn(Optional.empty());

       assertThrows(IllegalArgumentException.class, () -> beerService.getBeerById(beerId));
    }
}
