package com.sd.sdzx.order.service;

import com.github.pagehelper.PageInfo;
import com.sd.sdzx.model.dto.h5.OrderInfoDto;
import com.sd.sdzx.model.entity.order.OrderInfo;
import com.sd.sdzx.model.vo.h5.TradeVo;

public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo) ;

    void updateOrderStatus(String orderNo, Integer orderStatus);
}
