package com.example.expert.configurations.database;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for connecting database Db1.
 *
 * @author Vladimir Goncharenko
 * email vldmrgon@gmail.com
 * @since 07/11/2021
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager",
        basePackages = {"com.example.expert.repositories"}
)
public class Db1Config {

    private static final String NAME_DATABASE = "db1";
    private static final String PACKAGE_WITH_ENTITIES = "com.example.expert.domain.entities.db1";

    @Primary
    @Bean(NAME_DATABASE + "DataSourceProperties")
    @ConfigurationProperties(prefix = "application." + NAME_DATABASE + ".datasource")
    public DataSourceProperties dbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(NAME_DATABASE + "HikariDataSource")
    @ConfigurationProperties("application." + NAME_DATABASE + ".datasource.hikari")
    public HikariDataSource dbHikariDataSource(
            @Qualifier(NAME_DATABASE + "DataSourceProperties") DataSourceProperties dbDataSourceProperties) {
        return (HikariDataSource) dbDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(NAME_DATABASE + "JpaProperties")
    @ConfigurationProperties("application." + NAME_DATABASE + ".jpa")
    public JpaProperties dbJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean(NAME_DATABASE + "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier(NAME_DATABASE + "HikariDataSource") DataSource dataSource,
            @Qualifier(NAME_DATABASE + "JpaProperties") JpaProperties dbJpaProperties,
            HibernateProperties hibernateProperties
    ) {
        Map<String, Object> properties =
                hibernateProperties.determineHibernateProperties(dbJpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(dataSource)
                .packages(PACKAGE_WITH_ENTITIES)
                .persistenceUnit(NAME_DATABASE)
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(NAME_DATABASE + "TransactionManager")
    public PlatformTransactionManager dbTransactionManager(
            @Qualifier(NAME_DATABASE + "EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
