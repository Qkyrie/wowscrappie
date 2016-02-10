package com.deswaef.weakauras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories(basePackages = "com.deswaef")
@ComponentScan(basePackages = "com.deswaef")
@EnableAsync
@SpringBootApplication
public class WowScrappie {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WowScrappie.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run();
    }
}
