package com.sd.sdzx.product.controller;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.feign.user.UserFeignClient;
import com.sd.sdzx.model.dto.h5.ProductSkuDto;
import com.sd.sdzx.model.dto.product.SkuSaleDto;
import com.sd.sdzx.model.entity.product.ProductSku;
import com.sd.sdzx.model.entity.user.UserBrowseHistory;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import com.sd.sdzx.model.vo.h5.ProductItemVo;
import com.sd.sdzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value="/api/product")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserFeignClient userFeignClient;

    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Integer page,
                                                   @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Integer limit,
                                                   @Parameter(name = "productSkuDto", description = "搜索条件对象", required = false) ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
//    @Operation(summary = "商品详情")
//    @GetMapping("item/{skuId}")
//    public Result<ProductItemVo> item(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
//        ProductItemVo productItemVo = productService.item(skuId);
//        return Result.build(productItemVo , ResultCodeEnum.SUCCESS);
//    }
@Operation(summary = "商品详情")
@GetMapping("item/{skuId}")
public Result<ProductItemVo> item(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable String skuId) {
    ProductItemVo productItemVo;
    if (!"undefined".equals(skuId)) {
        // 如果没有传入有效的skuId，可以返回收藏最多的商品信息
        //远程调用查询收藏最多的商品
        productItemVo = productService.item(skuId);
    } else {
        //远程调用获取浏览量最多的商品
        UserBrowseHistory browseHistory = userFeignClient.getByBrowseHistory();
        Long id = browseHistory.getSkuId();
        String skuidString = String.valueOf(id);
        productItemVo = productService.item(skuidString);
    }

    return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
}

    //远程调用
    @Operation(summary = "获取商品sku信息")
    @GetMapping("getBySkuId/{skuId}")
    public ProductSku getBySkuId(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }
    @Operation(summary = "更新商品sku销量")
    @PostMapping("updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
        return productService.updateSkuSaleNum(skuSaleDtoList);
    }

}