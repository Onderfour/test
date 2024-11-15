package com.sd.sdzx.product.service;

import com.sd.sdzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();
}
