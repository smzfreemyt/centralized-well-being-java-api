package com.cewb.app.service;

import com.cewb.app.model.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Page<Category> findAll(int pageNum);

    Page<Category> findByKeyword(int pageNum, String keyword);

    Category findById(Long id);

    Category save(Category category) throws Exception;

    Category delete(Long id);

}
