package com.deswaef.wowscrappie

import org.springframework.boot.autoconfigure.{SpringBootApplication, EnableAutoConfiguration}
import org.springframework.context.annotation.{Configuration, ComponentScan}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Created by QuintenDes on 29/07/15.
 */
@EnableJpaRepositories(basePackages = Array("com.deswaef"))
@ComponentScan(basePackages = Array("com.deswaef"))
@EnableAsync
@SpringBootApplication
class WowScrappie {

}
