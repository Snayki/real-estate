package com.real.estate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.real.estate.domain.Ad;
import com.real.estate.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Snayki on 22.03.2016.
 */
public interface AdService {
    void indexAds(City city);

    Page<Ad> findAll(Pageable pageable, JsonNode query);
}
