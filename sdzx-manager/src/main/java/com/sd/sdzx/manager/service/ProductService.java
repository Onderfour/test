package com.sd.sdzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.product.ProductDto;
import com.sd.sdzx.model.entity.product.Product;

public interface ProductService {
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    Product getById(Long id);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
