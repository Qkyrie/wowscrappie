package com.deswaef;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.deswaef")
@ComponentScan(basePackages = "com.deswaef")
@SpringBootApplication
public class WowScrappieTestInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WowScrappieTestInitializer.class);
        application.run();
    }

}
