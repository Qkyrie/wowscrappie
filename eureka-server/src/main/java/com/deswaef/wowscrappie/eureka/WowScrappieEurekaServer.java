package com.deswaef.wowscrappie.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WowScrappieEurekaServer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WowScrappieEurekaServer.class);
        app.addListeners(new ApplicationPidFileWriter("wowscrappie-eureka.pid"));
        app.run();
    }
}
