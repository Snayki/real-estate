package com.real.estate.utils;

import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.data.elasticsearch.core.FacetedPage;

import java.util.List;

/**
 * Created by Snayki on 25.03.2016.
 */
public interface CustomFacetedPage<T> extends FacetedPage<T> {
    List<Aggregation> getAggregations();
}
