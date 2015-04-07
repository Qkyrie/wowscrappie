package com.deswaef.weakauras.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * User: Quinten
 * Date: 25-7-2014
 * Time: 21:20
 *
 * @author Quinten De Swaef
 */
@Configuration
public class ThymeleafConfiguration {

    @Autowired
    private final Collection<ITemplateResolver> templateResolvers = Collections
            .emptySet();

    @Autowired(required = false)
    private final Collection<IDialect> dialects = Collections.emptySet();

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        this.templateResolvers.forEach(engine::addTemplateResolver);
        this.dialects.forEach(engine::addDialect);
        engine.addDialect(new SpringSecurityDialect());
        /*
            EDIT: added the additional spring security dialect to the template resolver
         */
        return engine;
    }

    @Bean(name = "sysprops")
    public Properties provideProperties() {
        return System.getProperties();
    }
}

