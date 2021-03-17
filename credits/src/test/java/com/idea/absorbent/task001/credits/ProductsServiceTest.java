package com.idea.absorbent.task001.credits;

import com.idea.absorbent.task001.credits.services.ProductsService;
import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.web.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class ProductsServiceTest {

    private ProductsService testedService;
    private RestTemplate restTemplateMock;

    private final String testServiceUrl = "https://host:port/api/customers";

    public ProductsServiceTest() {
        this.restTemplateMock = Mockito.mock(RestTemplate.class);
        this.testedService = new ProductsService(testServiceUrl, restTemplateMock);
    }

    @Test
    void shouldGetCustomersById() {
        //Given a response from remote service and resource uri
        Set<Integer> productsId =Set.of(2,3,64,25);
        Iterator<Integer> iter = productsId.iterator();
        ProductDto[] responseBody = new ProductDto[] {
                new ProductDto(iter.next(), 86350),
                new ProductDto(iter.next(), 71070),
                new ProductDto(iter.next(), 814070),
                new ProductDto(iter.next(), 254640)
        };

        ResponseEntity<ProductDto[]> response = new ResponseEntity<>(responseBody, HttpStatus.OK);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(testServiceUrl);
        builder.queryParam("creditsIds", productsId);
        URI properUri = builder.build().toUri();

        final ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);

        Mockito.when(restTemplateMock.getForEntity(uriCaptor.capture(), Mockito.eq(ProductDto[].class))).thenReturn(response);

        //When getting customers from service
        Set<ProductDto> productsDto = this.testedService.getProductsByCustomerId(productsId);

        //Then constructs proper uri and returns customers
        final URI usedUri = uriCaptor.getValue();

        assertEquals(properUri, usedUri);
        assertEquals(responseBody.length, productsDto.size());
    }

    @Test
    void shouldCreateCustomer() {
        //Given a valid customer request
        CreateProductRequestBody createProductDto = new CreateProductRequestBody(22, 735341);

        final ArgumentCaptor<String> urlStringCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<CreateProductRequestBody> dtoCaptor = ArgumentCaptor.forClass(CreateProductRequestBody.class);

        ProductDto response = new ProductDto(
                createProductDto.getCreditId(),
                createProductDto.getValue());

        Mockito.when(restTemplateMock.postForObject(
                urlStringCaptor.capture(),
                dtoCaptor.capture(),
                Mockito.eq(ProductDto.class))
        ).thenReturn(response);

        //When creating a customer
        ProductDto productDto = this.testedService.createProduct(createProductDto);

        //Then
        assertEquals(testServiceUrl, urlStringCaptor.getValue());
        assertEquals(createProductDto, dtoCaptor.getValue());
        assertEquals(response, productDto);
    }

    @Test
    void shouldDeleteProduct() {
        //Given an existing product id
        final int idToRemove = 77;
        final String deleteUrl = testServiceUrl + "/{id}";
        final URI properUri = UriComponentsBuilder.fromUriString(deleteUrl)
                .buildAndExpand(Collections.singletonMap("id", idToRemove))
                .toUri();

        final ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);

        Mockito.doNothing().when(restTemplateMock).delete(uriCaptor.capture());
        //When removing product
        this.testedService.removeProduct(idToRemove);

        //Then uses proper uri
        assertEquals(properUri, uriCaptor.getValue());

    }

    @BeforeEach
    private void resetMocks() {
        Mockito.reset(restTemplateMock);
    }
}
