package com.idea.absorbent.credits.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomerServiceUrl.class)
public class AdditionalConfiguration {

    private final CustomerServiceUrl customerServiceUrl;

    @Autowired
    public AdditionalConfiguration(CustomerServiceUrl customerServiceUrl) {
        this.customerServiceUrl = customerServiceUrl;
    }
}