package com.sd.sdzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.manager.service.OrderInfoService;
import com.sd.sdzx.model.dto.h5.OrderInfoDto;
import com.sd.sdzx.model.dto.order.OrderStatisticsDto;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.order.OrderInfo;
import com.sd.sdzx.model.entity.order.OrderItem;
import com.sd.sdzx.model.entity.product.Brand;
import com.sd.sdzx.model.entity.system.SysUser;
import com.sd.sdzx.model.vo.common.Result;
import com.sd.sdzx.model.vo.common.ResultCodeEnum;
import com.sd.sdzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService ;

    @GetMapping("/getOrderStatisticsData")
    public Result<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto) ;
        return Result.build(orderStatisticsVo , ResultCodeEnum.SUCCESS) ;
    }
//    @GetMapping("/{page}/{limit}")
//    public Result<PageInfo<OrderInfo>> findByPage(OrderInfo orderInfo, @PathVariable Integer page, @PathVariable Integer limit) {
//        PageInfo<OrderInfo> pageInfo = orderInfoService.findByPage(orderInfo,page, limit);
//        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
//    }
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<OrderInfo>> findByPage(SysUserDto sysUserDto,
                                                  @PathVariable Integer page,
                                                  @PathVariable Integer limit) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.findByPage(sysUserDto,page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping("/findAll")
    public Result findAll() {
        List<OrderInfo> list = orderInfoService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping("/details")
    public Result getOrderDetails(@RequestParam String orderNo, @RequestParam Long orderId) {
        OrderInfo orderInfoWithDetails = orderInfoService.getOrderInfoWithDetails(orderNo, orderId);
        if (orderInfoWithDetails != null) {
            return Result.build(orderInfoWithDetails, ResultCodeEnum.SUCCESS);
        } else {
            return Result.build(null, ResultCodeEnum.DATA_ERROR);
        }
    }

    @PutMapping(value = "/updateOrderInfo")
    public Result updateOrderInfo(@RequestBody OrderInfo orderInfo) {
        orderInfoService.updateOrderInfo(orderInfo);
        return  Result.build(orderInfo,ResultCodeEnum.SUCCESS);
    }


}