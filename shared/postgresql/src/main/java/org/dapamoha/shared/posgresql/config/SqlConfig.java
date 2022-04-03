package org.dapamoha.shared.posgresql.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan("org.dapamoha.shared.posgresql.entity")
@EnableJpaRepositories(basePackages = "org.dapamoha.shared.posgresql.repository")
@Configuration
@EnableTransactionManagement
public class SqlConfig {

    @Value("${org.dapamoha.shared.config.repository.sql.url}")
    private String url;

    @Value("${org.dapamoha.shared.config.repository.sql.username}")
    private String username;

    @Value("${org.dapamoha.shared.config.repository.sql.password}")
    private String password;

    @Value("${org.dapamoha.shared.config.repository.sql.databasePlatform}")
    private String databasePlatform;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databasePlatform);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
