package com.example.ProductMenager_service.Api;

import com.example.ProductMenager_data.entity.Product;
import com.example.ProductMenager_data.repository.ProductRepository;
import com.example.ProductMenager_service.dto.ProductDto;
import com.example.ProductMenager_service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductService {

    private final ProductRepository productRepository;
    private final FakeStoreApiClient fakeStoreApiClient;

    public ExternalProductService(ProductRepository productRepository, FakeStoreApiClient fakeStoreApiClient) {
        this.productRepository = productRepository;
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    // Obsługa lokalnej bazy danych
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // -------------------- Obsługa produktów z zewnętrznego API --------------------

    public List<ProductDto> getAllProductsFromApi() {
        return fakeStoreApiClient.getAllProducts();
    }

    public ProductDto getProductFromApiById(Long id) {
        return fakeStoreApiClient.getProductById(id);
    }

    public List<String> getAllCategoriesFromApi() {
        return fakeStoreApiClient.getAllCategories();
    }
    // -------------------- Obsługa użytkowników z zewnętrznego API --------------------
    public List<UserDto> getAllUsersFromApi() {
        return fakeStoreApiClient.getAllUsers();
    }

    public UserDto getUserFromApiById(Long id) {
        return fakeStoreApiClient.getUserById(id);
    }
}