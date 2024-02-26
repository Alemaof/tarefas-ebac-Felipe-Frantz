package br.com.ffrantz.vendas.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.ffrantz.vendas.online.repository")
public class MongoConfig {
}
