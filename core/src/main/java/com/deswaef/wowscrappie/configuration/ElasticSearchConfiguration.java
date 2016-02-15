package com.deswaef.wowscrappie.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.deswaef")
public class ElasticSearchConfiguration {
}
