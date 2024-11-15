package com.sd.sdzx.product.mapper;

import com.sd.sdzx.model.dto.h5.ProductSkuDto;
import com.sd.sdzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    ProductSku getById(Long skuId);

    List<ProductSku> findByProductId(Long productId);

    void updateSale(@Param("skuId") Long skuId, @Param("num") Integer num);
}
