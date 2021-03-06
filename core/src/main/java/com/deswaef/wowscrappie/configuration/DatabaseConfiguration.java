package com.deswaef.wowscrappie.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Profile("!integrationtest")
public class DatabaseConfiguration {

    @Value("${db.driver}")
    private String databaseDriver;
    @Value("${db.password}")
    private String databasePassword;
    @Value("${db.url}")
    private String databaseUrl;
    @Value("${db.username}")
    private String databaseUsername;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String ddlGeneration;
    @Value("${hibernate.show_sql}")
    private String showSql;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setConnectionTestQuery("select 1 from dual");
        config.setUsername(databaseUsername);
        config.setPassword(databasePassword);
        config.setDriverClassName(databaseDriver);
        config.setJdbcUrl(databaseUrl);
        config.setMaximumPoolSize(20);
        return new HikariDataSource(config);
    }


    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_PACKAGES_TO_SCAN = "com.deswaef";

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);
        Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, hibernateDialect);
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, hibernateFormatSql);
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, ddlGeneration);
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, showSql);

        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }
}
