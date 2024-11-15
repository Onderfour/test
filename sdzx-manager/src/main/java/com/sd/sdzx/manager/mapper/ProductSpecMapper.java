package com.sd.sdzx.manager.mapper;

import com.sd.sdzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductSpecMapper {
    public List<ProductSpec> findByPage() ;

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);

    List<ProductSpec> findAll();
}
