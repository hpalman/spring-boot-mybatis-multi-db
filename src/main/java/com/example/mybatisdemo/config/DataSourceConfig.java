package com.example.mybatisdemo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataSourceConfig {

    // Schema 1 DataSource Configuration
    @Primary
    @Bean(name = "schema1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.schema1")
    DataSource schema1DataSource() {
    	log.info("called");
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "schema1SqlSessionFactory")
    SqlSessionFactory schema1SqlSessionFactory(@Qualifier("schema1DataSource") DataSource dataSource) throws Exception {
    	log.info("called");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/schema1/*.xml"));
        return sessionFactory.getObject();
    }

    @Primary
    @Bean(name = "schema1TransactionManager")
    DataSourceTransactionManager schema1TransactionManager(@Qualifier("schema1DataSource") DataSource dataSource) {
    	log.info("called");
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "schema1SqlSessionTemplate")
    SqlSessionTemplate schema1SqlSessionTemplate(@Qualifier("schema1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    	log.info("called");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // Schema 2 DataSource Configuration
    @Bean(name = "schema2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.schema2")
    DataSource schema2DataSource() {
    	log.info("called");
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Bean(name = "schema2SqlSessionFactory")
    SqlSessionFactory schema2SqlSessionFactory(@Qualifier("schema2DataSource") DataSource dataSource) throws Exception {
    	log.info("called");
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));

        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/schema2/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "schema2TransactionManager")
    DataSourceTransactionManager schema2TransactionManager(@Qualifier("schema2DataSource") DataSource dataSource) {
    	log.info("called");
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "schema2SqlSessionTemplate")
    SqlSessionTemplate schema2SqlSessionTemplate(@Qualifier("schema2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    	log.info("called");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
