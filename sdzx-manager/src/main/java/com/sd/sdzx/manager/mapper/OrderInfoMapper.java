package com.sd.sdzx.manager.mapper;


import com.sd.sdzx.model.dto.h5.OrderInfoDto;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.order.OrderInfo;
import com.sd.sdzx.model.entity.order.OrderItem;
import com.sd.sdzx.model.entity.order.OrderStatistics;
import com.sd.sdzx.model.entity.pay.PaymentInfo;
import com.sd.sdzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(String createTime);



//    List<OrderInfo> findByPage(OrderInfoDto orderInfoDto);
//    List<OrderInfo> findByPage(OrderInfo orderInfo);
    List<OrderInfo> findByPage(SysUserDto sysUserDto);

    List<OrderInfo> findAll();

    OrderInfo getOrderInfoWithDetails(@Param("orderNo") String orderNo, @Param("orderId") Long orderId);


    void updateByOrderId(OrderInfo orderInfo);

    void updateByPayId(PaymentInfo paymentInfo);
    void updateByItemId(OrderItem orderItem);

    PaymentInfo selectByPayId(String orderNo);
}
