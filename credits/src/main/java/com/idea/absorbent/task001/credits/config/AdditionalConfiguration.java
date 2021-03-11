package com.idea.absorbent.task001.credits.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomerServiceUrl.class)
public class AdditionalConfiguration {

    private CustomerServiceUrl customerServiceUrl;

    @Autowired
    public AdditionalConfiguration(CustomerServiceUrl customerServiceUrl) {
        this.customerServiceUrl = customerServiceUrl;
    }
}