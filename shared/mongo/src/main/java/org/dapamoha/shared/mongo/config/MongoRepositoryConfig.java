package org.dapamoha.shared.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "org.dapamoha.shared.mongo.repository")
@Configuration
public class MongoRepositoryConfig extends AbstractMongoClientConfiguration {

    @Value("${org.dapamoha.shared.config.repository.mongo.database}")
    private String database;

    @Value("${org.dapamoha.shared.config.repository.mongo.username}")
    private String username;

    @Value("${org.dapamoha.shared.config.repository.mongo.password}")
    private String password;

    @Value("${org.dapamoha.shared.config.repository.mongo.mapping-base-Packages}")
    private String mappingBasePackages;

    @Value("${org.dapamoha.shared.config.repository.mongo.uri}")
    private String uri;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {

        MongoCredential credential = MongoCredential.createCredential(
                username, getDatabaseName(),
                password.toCharArray());

        ConnectionString connectionString = new ConnectionString(uri);

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(credential)
                .build();


        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton(mappingBasePackages);
    }
}
