package ru.geekbrains.domodel.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:postgres.properties")
public class DbConfig {
    @Bean
    public DataSource dataSource(
            @Value("${db.host}") String dbHost,
            @Value("${db.port}") String dbPort,
            @Value("${db.name}") String dbName,
            @Value("${db.username}") String dbUsername,
            @Value("${db.password}") String dbPassword) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s?useUnicode=true&characterEncoding=utf8", dbHost, dbPort, dbName));
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}
