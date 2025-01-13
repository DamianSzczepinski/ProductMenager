package com.example.ProductMenager_service;

import com.example.ProductMenager_data.entity.Product;
import com.example.ProductMenager_data.repository.ProductRepository;
import com.example.ProductMenager_service.Api.FakeStoreApiClient;
import com.example.ProductMenager_service.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final FakeStoreApiClient fakeStoreApiClient;

    public ProductService(ProductRepository productRepository, FakeStoreApiClient fakeStoreApiClient) {
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

    // Obsługa danych z zewnętrznego API
    public List<ProductDto> getAllProductsFromApi() {
        try {
            List<ProductDto> products = fakeStoreApiClient.getAllProducts();
            log.info("Pobrano {} produktów z FakeStoreApi", products.size());
            return products;
        } catch (Exception e) {
            log.error("Błąd podczas komunikacji z FakeStoreApi: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Nie udało się pobrać danych z FakeStoreApi. Spróbuj ponownie później.", e);
        }
    }

    public ProductDto getProductFromApiById(Long id) {
        try {
            return fakeStoreApiClient.getProductById(id);
        } catch (Exception e) {
            log.error("Błąd podczas pobierania produktu o ID {} z FakeStoreApi: {}", id, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Nie udało się pobrać produktu z FakeStoreApi. Spróbuj ponownie później.", e);
        }
    }

    public List<String> getAllCategoriesFromApi() {
        try {
            return fakeStoreApiClient.getAllCategories();
        } catch (Exception e) {
            log.error("Błąd podczas pobierania kategorii z FakeStoreApi: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Nie udało się pobrać kategorii z FakeStoreApi. Spróbuj ponownie później.", e);
        }
    }
}