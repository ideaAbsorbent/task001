package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@Service
public class ProductsService {

    private String productsUri;
    private RestTemplate restTemplate;

    public ProductsService(@Value("${app.properties.products.service.url}") String customersUrl) {
        this.productsUri = customersUrl;
        this.restTemplate = new RestTemplate();
    }

    public Set<ProductDto> getCustomersByCreditIds(Set<Integer> ids) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(productsUri);
        builder.queryParam("creditsIds", ids);
        URI uri = builder.build().toUri();
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(uri, ProductDto[].class);
        return Set.of(response.getBody());
    }

    public ProductDto creatProduct(CreateProductRequestBody dto) {
        return restTemplate.postForObject(productsUri, dto, ProductDto.class);
    }
}
