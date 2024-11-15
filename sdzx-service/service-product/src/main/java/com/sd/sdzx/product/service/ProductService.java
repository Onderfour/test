package com.sd.sdzx.product.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.h5.ProductSkuDto;
import com.sd.sdzx.model.dto.product.SkuSaleDto;
import com.sd.sdzx.model.entity.product.ProductSku;
import com.sd.sdzx.model.vo.h5.ProductItemVo;

import java.util.List;

public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(String skuId);

    ProductSku getBySkuId(Long skuId);

    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);
}
