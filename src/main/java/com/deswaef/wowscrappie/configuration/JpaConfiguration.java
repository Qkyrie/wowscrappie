package com.deswaef.wowscrappie.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * Created by QuintenDes on 20/08/15.
 */
@EnableJpaRepositories(basePackages = "com.deswaef")
@Configuration
public class JpaConfiguration {
    @PostConstruct
    public void init() {
        System.out.println("lol");
    }
}
