package com.twoDB.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.twoDB.repository.secondary",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondaryDataSource() {
        return secondaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource())
                .packages("com.twoDB.entity")
                .persistenceUnit("secondary")
                .build();
    }

    @Bean
    public JpaTransactionManager secondaryTransactionManager(LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory.getObject());
    }
}

