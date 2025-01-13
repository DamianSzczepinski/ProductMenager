package com.example.ProductMenager_api;

import com.example.ProductMenager_data.entity.Product;
import com.example.ProductMenager_service.ProductService;
import com.example.ProductMenager_service.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // -------------------- LOKALNA BAZA DANYCH --------------------
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- ZEWNĘTRZNE API --------------------
    @GetMapping("/external")
    public ResponseEntity<List<ProductDto>> getAllProductsFromApi() {
            List<ProductDto> products = productService.getAllProductsFromApi();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/external/{id}")
    public ResponseEntity<ProductDto> getProductFromApiById(@PathVariable Long id) {
        ProductDto product = productService.getProductFromApiById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/external/categories")
    public ResponseEntity<List<String>> getAllCategoriesFromApi() {
        List<String> categories = productService.getAllCategoriesFromApi();
        return ResponseEntity.ok(categories);
    }
}


