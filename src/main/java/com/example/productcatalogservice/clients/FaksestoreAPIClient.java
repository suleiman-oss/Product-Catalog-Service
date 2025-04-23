package com.example.productcatalogservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.productcatalogservice.dtos.FakestoreProductDTO;
@Component
public class FaksestoreAPIClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakestoreProductDTO getProductById(Long id){
        ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity= requetsForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",null,FakestoreProductDTO.class,id);
        if(fakestoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return fakestoreProductDTOResponseEntity.getBody();
        }
        return null;
    }

    public FakestoreProductDTO[] getAllProducts(){
        FakestoreProductDTO[] fakestoreProductDTOs = requetsForEntity(HttpMethod.GET,"https://fakestoreapi.com/products",null, FakestoreProductDTO[].class).getBody();
        if (fakestoreProductDTOs==null){
            return null;
        }
        return fakestoreProductDTOs;
    }

    public FakestoreProductDTO replaceProduct(Long id, FakestoreProductDTO fakestoreProductDTOreq){
        FakestoreProductDTO fakestoreProductDTO=requetsForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}", fakestoreProductDTOreq, FakestoreProductDTO.class,id).getBody();
        return fakestoreProductDTO;
    }

    public <T> ResponseEntity<T> requetsForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate=restTemplateBuilder.build();
		RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
		ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
		return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
	}
}
