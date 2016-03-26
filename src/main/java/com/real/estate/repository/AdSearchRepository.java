package com.real.estate.repository;

import com.real.estate.domain.Ad;
import com.real.estate.utils.CustomFacetedPage;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Snayki on 22.03.2016.
 */
@Repository
public interface AdSearchRepository extends ElasticsearchRepository<Ad, String> {

    void deleteByCity(String city);

    CustomFacetedPage<Ad> search(SearchQuery searchQuery);
}
