package com.example.productcatalogservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.productcatalogservice.dtos.FakestoreProductDTO;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;

@Service
public class ProductService implements IProductService {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id){
        ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity= requetsForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",null,FakestoreProductDTO.class,id);
        if(fakestoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            return from(fakestoreProductDTOResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<>();
        FakestoreProductDTO[] fakestoreProductDTOs = requetsForEntity(HttpMethod.GET,"https://fakestoreapi.com/products",null, FakestoreProductDTO[].class).getBody();
        if (fakestoreProductDTOs==null){
            return null;
        }
        for(FakestoreProductDTO fakestoreProductDTO:fakestoreProductDTOs){
            products.add(from(fakestoreProductDTO));
        }
        return products;
    }
    
    @Override
    public Product replaceProduct(Long id, Product product){
        FakestoreProductDTO fakestoreProductDTOreq = from(product);
        FakestoreProductDTO fakestoreProductDTO=requetsForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}", fakestoreProductDTOreq, FakestoreProductDTO.class,id).getBody();
        return from(fakestoreProductDTO);
    }

    public <T> ResponseEntity<T> requetsForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate=restTemplateBuilder.build();
		RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
		ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
		return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
	}

    @Override
    public Product createProduct(Product product){
        return product;
    }

    private Product from(FakestoreProductDTO fakestoreProductDTO){
        Product product=new Product();
        product.setId(fakestoreProductDTO.getId());
        product.setName(fakestoreProductDTO.getTitle());
        product.setImageURL(fakestoreProductDTO.getImage());
        product.setDescription(fakestoreProductDTO.getDescription());
        product.setPrice(fakestoreProductDTO.getPrice());
        Category category=new Category();
        category.setDescription(fakestoreProductDTO.getCategory());
        category.setName(fakestoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }
    private FakestoreProductDTO from(Product product){
        FakestoreProductDTO fakestoreProductDTO=new FakestoreProductDTO();
        fakestoreProductDTO.setId(product.getId());
        fakestoreProductDTO.setTitle(product.getName());
        fakestoreProductDTO.setImage(product.getImageURL());
        fakestoreProductDTO.setDescription(product.getDescription());
        fakestoreProductDTO.setCategory(product.getCategory().getName());
        fakestoreProductDTO.setPrice(product.getPrice());
        return fakestoreProductDTO;
    }

}
