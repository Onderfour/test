package com.sd.sdzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.h5.OrderInfoDto;
import com.sd.sdzx.model.dto.order.OrderStatisticsDto;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.order.OrderInfo;
import com.sd.sdzx.model.entity.product.Brand;
import com.sd.sdzx.model.entity.user.UserInfo;
import com.sd.sdzx.model.vo.order.OrderStatisticsVo;

import java.util.List;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
    //    List<OrderInfo> findByPage(OrderInfoDto orderInfoDto);
//    PageInfo<OrderInfo> findByPage(OrderInfo orderInfo ,Integer page, Integer limit);
    PageInfo<OrderInfo> findByPage(SysUserDto sysUserDto, Integer page, Integer limit);

    List<OrderInfo> findAll();
    public OrderInfo getOrderInfoWithDetails(String orderNo, Long orderId);

    void updateOrderInfo(OrderInfo orderInfo);
}
