package com.deswaef.wowscrappie.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@EnableJpaRepositories(basePackages = "com.deswaef")
@Configuration
public class JpaConfiguration { }
