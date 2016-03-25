package com.real.estate;

import com.real.estate.utils.CustomDefaultResultMapper;
import org.elasticsearch.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

@SpringBootApplication
public class RealEstateApplication {

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate(Client client, ElasticsearchConverter converter) {
		CustomDefaultResultMapper mapper = new CustomDefaultResultMapper(converter.getMappingContext());
		return new ElasticsearchTemplate(client, converter, mapper);
	}

	public static void main(String[] args) {
		SpringApplication.run(RealEstateApplication.class, args);
	}
}
