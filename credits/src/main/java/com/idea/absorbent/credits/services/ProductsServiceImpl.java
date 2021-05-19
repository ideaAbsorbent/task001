package com.idea.absorbent.credits.services;

import com.idea.absorbent.credits.dto.CreateProductRequestBody;
import com.idea.absorbent.credits.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

@Service
public class ProductsServiceImpl implements ProductsService{

    private final String productsUri;
    private final RestTemplate restTemplate;

    @Autowired
    public ProductsServiceImpl(@Value("${app.properties.products.service.url}") String customersUrl) {
        this.productsUri = customersUrl;
        this.restTemplate = new RestTemplate();
    }

    public ProductsServiceImpl(
            @Value("${app.properties.products.service.url}") String productsUri,
            RestTemplate restTemplate) {
        this.productsUri = productsUri;
        this.restTemplate = restTemplate;
    }

    public Set<ProductDto> getProductsByCustomerId(Set<Integer> ids) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(productsUri);
        builder.queryParam("creditsIds", ids);
        URI uri = builder.build().toUri();
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(uri, ProductDto[].class);
        return Set.of(response.getBody());
    }

    public ProductDto createProduct(CreateProductRequestBody dto) {
        return restTemplate.postForObject(productsUri, dto, ProductDto.class);
    }

    public void removeProduct(int productId) {
        String deleteUri = productsUri + "/{id}";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(deleteUri).buildAndExpand(Collections.singletonMap("id", productId));
        restTemplate.delete(uriComponents.toUri());
    }
}
