package com.example.ProductMenager_service.Api;

import com.example.ProductMenager_service.dto.ProductDto;
import com.example.ProductMenager_service.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreApiClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public FakeStoreApiClient(RestTemplate restTemplate,
                              @Value("${fakestoreapi.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<ProductDto> getAllProducts() {
        String url = baseUrl + "/products";
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, ProductDto[].class)));
    }

    public ProductDto getProductById(Long id) {
        String url = baseUrl + "/products/" + id;
        return restTemplate.getForObject(url, ProductDto.class);
    }

    public List<String> getAllCategories() {
        String url = baseUrl + "/products/categories";
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, String[].class)));
    }
    // -------------------- Użytkownicy --------------------
    public List<UserDto> getAllUsers() {
        String url = baseUrl + "/users";
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, UserDto[].class)));
    }

    public UserDto getUserById(Long id) {
        String url = baseUrl + "/users/" + id;
        return restTemplate.getForObject(url, UserDto.class);
    }
}