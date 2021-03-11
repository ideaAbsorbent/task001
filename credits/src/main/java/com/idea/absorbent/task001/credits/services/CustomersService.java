package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.web.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@Service
public class CustomersService {

    private String customersUrl;
    private RestTemplate restTemplate;

    public CustomersService(@Value("${app.properties.customers.service.url}") String customersUrl) {
        this.customersUrl = customersUrl;
        this.restTemplate = new RestTemplate();
    }

    public Set<CustomerDto> getCustomersByCreditIds(Set<Integer> ids) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(customersUrl);
        builder.queryParam("creditsIds", ids);
        URI uri = builder.build().toUri();
        ResponseEntity<CustomerDto[]>  response = restTemplate.getForEntity(uri, CustomerDto[].class);
        return Set.of(response.getBody());
    }

    public CustomerDto creatCustomer(CreateCustomerRequestBody dto) {
        return restTemplate.postForObject(customersUrl, dto, CustomerDto.class);
    }
}
