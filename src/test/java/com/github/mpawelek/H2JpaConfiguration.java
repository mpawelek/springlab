package com.github.mpawelek;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Profile("test")
public class H2JpaConfiguration {
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:h2:./target/h2.db");
        ds.setUsername("sa");
        ds.setDriverClassName("org.h2.Driver");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManager(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter, JpaProperties jpaProperties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties.getProperties());
        entityManagerFactoryBean.setPersistenceUnitName("a");
        entityManagerFactoryBean.setPackagesToScan("com.github.mpawelek");

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
//        return new HibernateJpaVendorAdapter();
        EclipseLinkJpaVendorAdapter vendor = new EclipseLinkJpaVendorAdapter();
        vendor.setDatabasePlatform("org.eclipse.persistence.platform.database.H2Platform");
        return vendor;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaProperties jpaProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("eclipselink.ddl-generation", "drop-and-create-tables");
        props.put("eclipselink.ddl-generation.output-mode", "database");
        props.put(PersistenceUnitProperties.WEAVING, InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static");
        props.put("eclipselink.logging.level", "FINEST");
        props.put("eclipselink.logging.level.sql", "FINE");
        props.put("eclipselink.logging.parameters", "true");
        props.put("eclipselink.orm.validate.schema", "true");
        props.put("eclipselink.cache.shared.default", "false");
        JpaProperties jpaProps = new JpaProperties();
        jpaProps.setProperties(props);
        return jpaProps;
    }
}
