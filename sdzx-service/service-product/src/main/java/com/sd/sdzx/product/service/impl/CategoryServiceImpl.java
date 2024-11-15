package com.sd.sdzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.sd.sdzx.model.entity.product.Category;
import com.sd.sdzx.product.mapper.CategoryMapper;
import com.sd.sdzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public List<Category> findOneCategory() {
        String catagoryOneJson = redisTemplate.opsForValue().get("category:one");
        if (StringUtils.hasText(catagoryOneJson)){
            List<Category> exitCategories = JSON.parseArray(catagoryOneJson, Category.class);
            return exitCategories;
        }

        List<Category> categoryList=categoryMapper.findOneCategory();
        redisTemplate.opsForValue().set("category:one",JSON.toJSONString(categoryList),
                7, TimeUnit.DAYS);
        return categoryList;
    }
    @Cacheable(value = "category" , key = "'all'")
    @Override
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryMapper.findAll();
        //全部一级分类
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)) {
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);

                if(!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}