package ru.bach.config;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = "ru.bach.bank_service.dao")
@EntityScan("ru.bach.bank_service.entity")
public class ServiceConfig {
}
