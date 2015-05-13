package com.deswaef.weakauras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.deswaef.weakauras")
@ComponentScan(basePackages = "com.deswaef.weakauras")
@EnableAsync
public class WeakAuras {
    public static void main(final String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(WeakAuras.class);
        springApplication
                .addListeners(new ApplicationPidFileWriter());
        springApplication.run();
    }
}
