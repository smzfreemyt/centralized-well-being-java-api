package com.cewb.app.service.impl;

import com.cewb.app.config.ConfigRepository;
import com.cewb.app.exception.ExceptionCatcher;
import com.cewb.app.model.Category;
import com.cewb.app.repository.CategoryRepository;
import com.cewb.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    ConfigRepository configRepo;

    @Override
    public Page<Category> findAll(int pageNum) {
        return categoryRepository.findAll(PageRequest.of(pageNum, configRepo.PER_PAGE));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cant find category with id - " + id));
    }

    @Override
    public Category save(Category category) throws Exception {
        if(this.categoryRepository.existsByName(category.getName())) {
            throw new ExceptionCatcher("Category name already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return category;
    }
}
