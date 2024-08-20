package com.example.adviceex.Controller;





import com.example.adviceex.Model.Category;
import com.example.adviceex.Service.CategoryService;
import com.example.adviceex.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/getall")
    public ResponseEntity getAllCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category) {
            categoryService.addNewCategory(category);
            return ResponseEntity.status(201).body("category added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body("category deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category) {

            categoryService.updateCategory(category, id);
            return ResponseEntity.status(201).body("category updated successfully");

    }

    @GetMapping("/get/pro/by/category/{cat}")
    public ResponseEntity getUserByCategory(@PathVariable String cat){
        return ResponseEntity.status(200).body(productService.getProductsByCategory(cat));
    }
}
