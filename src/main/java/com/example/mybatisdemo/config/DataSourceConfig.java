package com.example.mybatisdemo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // Schema 1 DataSource Configuration
    @Primary
    @Bean(name = "schema1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.schema1")
    public DataSource schema1DataSource() {
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "schema1SqlSessionFactory")
    public SqlSessionFactory schema1SqlSessionFactory(@Qualifier("schema1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/schema1/*.xml"));
        return sessionFactory.getObject();
    }

    @Primary
    @Bean(name = "schema1TransactionManager")
    public DataSourceTransactionManager schema1TransactionManager(@Qualifier("schema1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "schema1SqlSessionTemplate")
    public SqlSessionTemplate schema1SqlSessionTemplate(@Qualifier("schema1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // Schema 2 DataSource Configuration
    @Bean(name = "schema2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.schema2")
    public DataSource schema2DataSource() {
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
    }

    @Bean(name = "schema2SqlSessionFactory")
    public SqlSessionFactory schema2SqlSessionFactory(@Qualifier("schema2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/schema2/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "schema2TransactionManager")
    public DataSourceTransactionManager schema2TransactionManager(@Qualifier("schema2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "schema2SqlSessionTemplate")
    public SqlSessionTemplate schema2SqlSessionTemplate(@Qualifier("schema2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
