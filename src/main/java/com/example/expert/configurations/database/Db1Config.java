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

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "application.db1.datasource")
  public DataSourceProperties db1DataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("application.db1.datasource.hikari")
  public HikariDataSource db1HikariDataSource(
      @Qualifier("db1DataSourceProperties") DataSourceProperties db1DataSourceProperties) {
    return (HikariDataSource) db1DataSourceProperties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Primary
  @ConfigurationProperties("application.db1.jpa")
  public JpaProperties db1JpaProperties() {
    return new JpaProperties();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("db1HikariDataSource") DataSource dataSource,
      @Qualifier("db1JpaProperties") JpaProperties db1JpaProperties,
      HibernateProperties hibernateProperties) {
    Map<String, Object> properties =
        hibernateProperties.determineHibernateProperties(db1JpaProperties.getProperties(), new HibernateSettings());
    return builder
        .dataSource(dataSource)
        .packages("com.example.expert.domain.entities.db1")
        .persistenceUnit("db1")
        .properties(properties)
        .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager db1TransactionManager(
      @Qualifier("db1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
