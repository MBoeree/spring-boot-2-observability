package io.github.mboeree.springboot2observability;

import io.micrometer.stackdriver.StackdriverConfig;
import io.micrometer.stackdriver.StackdriverMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerStackdriverConfig {

    @Bean
    StackdriverConfig stackdriverConfig(@Value("${management.metrics.export.stackdriver.project-id}") String projectId) {
        return new StackdriverConfig() {
            @Override
            public String projectId() {
                return projectId;
            }

            @Override
            public String get(String key) {
                return null;
            }
        };
    }

    @Bean
    StackdriverMeterRegistry meterRegistry(StackdriverConfig stackdriverConfig, @Value("${spring.application.name}") String applicationName) {
        StackdriverMeterRegistry stackdriverMeterRegistry = StackdriverMeterRegistry.builder(stackdriverConfig).build();
        stackdriverMeterRegistry.config().commonTags("application", applicationName);
        return stackdriverMeterRegistry;
    }
}
