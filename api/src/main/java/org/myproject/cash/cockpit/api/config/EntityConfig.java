package org.myproject.cash.cockpit.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"org.myproject.cash.cockpit.api.repository.model"})
@EnableJpaRepositories(basePackages = {"org.myproject.cash.cockpit.api.repository"})
public class EntityConfig {
}
