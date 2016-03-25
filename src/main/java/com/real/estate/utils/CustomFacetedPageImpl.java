package com.real.estate.utils;

import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.facet.FacetResult;

import java.util.List;

/**
 * Created by Snayki on 25.03.2016.
 */
public class CustomFacetedPageImpl<T> extends FacetedPageImpl<T> implements CustomFacetedPage<T> {
    private List<Aggregation> aggregations;

    public CustomFacetedPageImpl(List<T> content, Pageable pageable, long total, List<FacetResult> facets, List<Aggregation> aggregations) {
        super(content, pageable, total, facets);
        this.aggregations = aggregations;
    }

    public List<Aggregation> getAggregations() {
        return aggregations;
    }
}
