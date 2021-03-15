package com.idea.absorbent.task001.credits;

import com.idea.absorbent.task001.credits.services.CustomersService;
import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.web.dto.CreateCustomerDto;
import com.idea.absorbent.task001.credits.web.dto.CustomerDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
public class CustomersServiceTest {

    private CustomersService testedService;
    private RestTemplate restTemplateMock;

    private final String testServiceUrl = "https://host:port/api/customers";

    public CustomersServiceTest() {
        this.restTemplateMock = Mockito.mock(RestTemplate.class);
        this.testedService = new CustomersService(testServiceUrl, restTemplateMock);
    }

    @Test
    void shouldGetCustomersById() {
        //Given a response from remote service and resource uri
        Set<Integer> creditsId =Set.of(2,3,64,25);
        Iterator<Integer> iter = creditsId.iterator();
        CustomerDto[] responseBody = new CustomerDto[] {
          new CustomerDto(iter.next(),"FirstName", "Surname","93121228929"),
          new CustomerDto(iter.next(),"FirstName", "Surname","92120695417"),
          new CustomerDto(iter.next(),"FirstName", "Surname","53021249934"),
          new CustomerDto(iter.next(),"FirstName", "Surname","60022372367")
        };

        ResponseEntity<CustomerDto[]> response = new ResponseEntity<>(responseBody, HttpStatus.OK);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(testServiceUrl);
        builder.queryParam("creditsIds", creditsId);
        URI properUri = builder.build().toUri();

        final ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);

        Mockito.when(restTemplateMock.getForEntity(uriCaptor.capture(), Mockito.eq(CustomerDto[].class))).thenReturn(response);

        //When getting customers from service
        Set<CustomerDto> customersDto = this.testedService.getCustomersByCreditIds(creditsId);

        //Then constructs proper uri and returns customers
        final URI usedUri = uriCaptor.getValue();

        assertEquals(properUri, usedUri);
        assertEquals(responseBody.length, customersDto.size());
    }

    @Test
    void shouldCreatCustomer() {
        //Given a valid customer request
        CreateCustomerRequestBody createCustomerDto = new CreateCustomerRequestBody(
        22,
        "FirstName",
        "Surname",
        "92120695417");

        final ArgumentCaptor<String> urlStringCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<CreateCustomerRequestBody> dtoCaptor = ArgumentCaptor.forClass(CreateCustomerRequestBody.class);

        CustomerDto response = new CustomerDto(
            createCustomerDto.getCreditId(),
            createCustomerDto.getFirstName(),
            createCustomerDto.getSurname(),
            createCustomerDto.getPesel());

        Mockito.when(restTemplateMock.postForObject(
            urlStringCaptor.capture(),
            dtoCaptor.capture(),
            Mockito.eq(CustomerDto.class))
        ).thenReturn(response);

        //When creating customer
        CustomerDto customerDto = this.testedService.creatCustomer(createCustomerDto);

        //Then
        assertEquals(testServiceUrl, urlStringCaptor.getValue());
        assertEquals(createCustomerDto, dtoCaptor.getValue());
        assertEquals(response, customerDto);
    }

    @BeforeEach
    private void resetMocks() {
        Mockito.reset(restTemplateMock);
    }
}
