package com.real.estate.utils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;

import java.util.List;

/**
 * Created by Snayki on 25.03.2016.
 */
public class CustomDefaultResultMapper extends DefaultResultMapper {

    public CustomDefaultResultMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext) {
        super(mappingContext);
    }

    @Override
    public <T> CustomFacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        FacetedPage<T> ts = super.mapResults(response, clazz, pageable);

        List<Aggregation> aggregations = response.getAggregations().asList();

        return new CustomFacetedPageImpl<T>(ts.getContent(), pageable, ts.getTotalElements(), ts.getFacets(), aggregations);
    }
}
