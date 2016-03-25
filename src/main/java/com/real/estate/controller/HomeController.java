package com.real.estate.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.real.estate.domain.Ad;
import com.real.estate.domain.City;
import com.real.estate.domain.Material;
import com.real.estate.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Snayki on 21.03.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class HomeController {

    @Autowired
    private AdService adService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public void indexCity(@RequestParam(value = "city") String cityName) {
        City city = City.cityOf(cityName);
        adService.indexAds(city);
    }

    @RequestMapping(value = "getAvailableCities", method = RequestMethod.GET)
    public List<City> getAvailableCities() {
        return City.getAll();
    }

    @RequestMapping(value = "getAvailableMaterials", method = RequestMethod.GET)
    public List<Material> getAvailableMaterials() {
        return Material.getAll();
    }

    @RequestMapping(value = "data", method = RequestMethod.GET)
    public Page<Ad> getAds(@PageableDefault(size = 5) Pageable page,
                           @RequestParam(value = "filterQuery", required = false) String query) throws IOException {
        JsonNode searchQuery = new ObjectMapper().readTree(query);
        return adService.findAll(page, searchQuery);
    }
}
