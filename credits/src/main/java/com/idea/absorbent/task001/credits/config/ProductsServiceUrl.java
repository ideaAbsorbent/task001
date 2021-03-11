package com.idea.absorbent.task001.credits.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.properties.products.service")
public class ProductsServiceUrl {
    private String url;
}



