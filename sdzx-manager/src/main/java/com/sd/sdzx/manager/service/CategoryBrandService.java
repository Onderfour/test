package com.sd.sdzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.product.CategoryBrandDto;
import com.sd.sdzx.model.entity.product.Brand;
import com.sd.sdzx.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
