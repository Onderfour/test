package com.sd.sdzx.manager.mapper;

import com.sd.sdzx.model.dto.product.ProductDto;
import com.sd.sdzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper {

    List<Product> findByPage(ProductDto productDto);

    void save(Product product);

    Product selectById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}
