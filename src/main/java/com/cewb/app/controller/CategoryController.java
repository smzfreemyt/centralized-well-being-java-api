package com.cewb.app.controller;

import com.cewb.app.model.Category;
import com.cewb.app.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Log4j2
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //Create category
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) throws Exception {
        log.info("Create category endpoint");
        return categoryService.save(category);
    }

    //View categories
    @GetMapping("/categories")
    public Page<Category> readCategories(@RequestParam(value = "page", defaultValue = "0") int pageNum){
        log.info("Read categories by page endpoint");
        return categoryService.findAll(pageNum);
    }

    //View category
    @GetMapping("/categories/{id}")
    public Category readCategory(@PathVariable Long id) {
        log.info("Read category with id " + id);
        return categoryService.findById(id);
    }

    //Update category
    @PutMapping("/categories")
    public Category updateCategory(@RequestBody Category category) throws Exception {
        log.info("Update company with id " + category.getId());
        categoryService.save(category);
        return category;
    }

    //Delete category
    @DeleteMapping("/categories/{id}")
    public Category deleteCategory(@PathVariable Long id) {
        log.info("Delete category with id " + id);
        return categoryService.delete(id);
    }
}
