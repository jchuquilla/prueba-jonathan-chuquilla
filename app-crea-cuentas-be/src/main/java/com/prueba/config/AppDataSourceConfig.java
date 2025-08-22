package com.prueba.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager", basePackages = {
        "com.prueba.repositories"
})
public class AppDataSourceConfig {

    @Bean("appDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.app")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            @Qualifier("appDatasource") DataSource dataSource
    ){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.prueba.entities");
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendor);
        factoryBean.setPersistenceUnitName("appPU");

        return factoryBean;
    }

    @Bean("appTransactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactory appEntityManagerFactory
    )
    {
        return new JpaTransactionManager(appEntityManagerFactory);
    }

    @Bean("appEntityManager")
    public EntityManager appEntityManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactory appEntityManagerFactory
    ){
        return appEntityManagerFactory.createEntityManager();
    }

}
