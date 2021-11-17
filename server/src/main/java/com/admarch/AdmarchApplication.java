package com.admarch;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.apache.commons.dbcp.BasicDataSource;


@SpringBootApplication
@EnableAutoConfiguration
//@ImportResource(value = { "classpath:application-config.xml"})
public class AdmarchApplication extends SpringBootServletInitializer {
    private static final Class<AdmarchApplication> applicationClass = AdmarchApplication.class;
    private static final Logger logger = LoggerFactory.getLogger(applicationClass);

    @Value("${db.driverclassname}")
    private String dbDriverClassName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;
    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        logger.info("dataSource url: " + dataSource.getUrl());
        return dataSource;
    }
}
