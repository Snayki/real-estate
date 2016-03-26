package com.real.estate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.real.estate.domain.Ad;
import com.real.estate.domain.City;
import com.real.estate.parser.Parser;
import com.real.estate.parser.impl.OtidoParser;
import com.real.estate.repository.AdSearchRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.real.estate.utils.Constants.*;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by Snayki on 22.03.2016.
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdSearchRepository adSearchRepository;

    @Transactional
    public void indexAds(City city) {
        Parser<Ad> parser = determineParser(city);
        List<Ad> ads = parser.parse()
                .stream()
                .map(parser::createFromElement)
                .collect(Collectors.toList());

        if (ads.size() > 0) {
            adSearchRepository.deleteByCity(city.getName());
            adSearchRepository.save(ads);
        }
    }

    @Override
    public Page<Ad> findAll(Pageable pageable, JsonNode query) {
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        query.fields().forEachRemaining(
                stringJsonNodeEntry -> prepareQueryBuilder(boolQueryBuilder, stringJsonNodeEntry)
        );

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withQuery(boolQueryBuilder)
                .addAggregation(getMinBuilder(PRICE_FIELD))
                .addAggregation(getMaxBuilder(PRICE_FIELD))
                .addAggregation(getAvgBuilder(PRICE_FIELD))
                .addAggregation(get25PercentilesBuilder(PRICE_FIELD))
                .addAggregation(get75PercentilesBuilder(PRICE_FIELD))
                .addAggregation(getMinBuilder(PRICE_PER_SQUARE_METER_FIELD))
                .addAggregation(getMaxBuilder(PRICE_PER_SQUARE_METER_FIELD))
                .addAggregation(getAvgBuilder(PRICE_PER_SQUARE_METER_FIELD))
                .addAggregation(get25PercentilesBuilder(PRICE_PER_SQUARE_METER_FIELD))
                .addAggregation(get75PercentilesBuilder(PRICE_PER_SQUARE_METER_FIELD))
                .withPageable(pageable)
                .build();

        return adSearchRepository.search(searchQuery);
    }

    private Parser<Ad> determineParser(City city) throws ValidationException {
        switch (city) {
            case Cherkasy:
                return new OtidoParser();
        }

        throw new ValidationException(UNSUPPORTED_CITY);
    }

    private void prepareQueryBuilder(BoolQueryBuilder boolQueryBuilder, Map.Entry<String, JsonNode> entry) {
        String fieldName = entry.getKey();
        JsonNode filter = entry.getValue();

        switch (filter.get(TYPE_PROP).asText()) {
            case MATCH_FILTER:
                boolQueryBuilder.must(matchQuery(fieldName, filter.get(VALUE_PROP).asText()));
                break;
            case RANGE_FILTER:
                boolQueryBuilder.must(rangeQuery(fieldName)
                        .gte(filter.get(FROM_PROP).asInt())
                        .lte(filter.get(TO_PROP).asInt()));
                break;
        }
    }

    private AbstractAggregationBuilder getMinBuilder(String fieldName) {
        return AggregationBuilders.min(String.format(MIN_AGGREGATION_NAME, fieldName)).field(fieldName);
    }

    private AbstractAggregationBuilder getMaxBuilder(String fieldName) {
        return AggregationBuilders.max(String.format(MAX_AGGREGATION_NAME, fieldName)).field(fieldName);
    }

    private AbstractAggregationBuilder getAvgBuilder(String fieldName) {
        return AggregationBuilders.avg(String.format(AVG_AGGREGATION_NAME, fieldName)).field(fieldName);
    }

    private AbstractAggregationBuilder get25PercentilesBuilder(String fieldName) {
        return AggregationBuilders.percentiles(String.format(PERCENTILES_25_AGGREGATION_NAME, fieldName))
                .field(fieldName).percentiles(PERCENTILES_25);
    }

    private AbstractAggregationBuilder get75PercentilesBuilder(String fieldName) {
        return AggregationBuilders.percentiles(String.format(PERCENTILES_75_AGGREGATION_NAME, fieldName))
                .field(fieldName).percentiles(PERCENTILES_75);
    }
}
