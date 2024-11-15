package com.sd.sdzx.product.mapper;

import com.sd.sdzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product getById(Long id);
}
