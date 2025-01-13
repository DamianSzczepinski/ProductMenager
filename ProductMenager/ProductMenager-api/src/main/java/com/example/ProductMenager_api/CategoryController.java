package com.example.ProductMenager_api;

import com.example.ProductMenager_data.entity.Category;
import com.example.ProductMenager_service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()) {
            return ResponseEntity.ok(categoryOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @RequestBody Category newCategory) {
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()) {
            Category existing = categoryOpt.get();
            existing.setName(newCategory.getName());
            return ResponseEntity.ok(categoryService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
