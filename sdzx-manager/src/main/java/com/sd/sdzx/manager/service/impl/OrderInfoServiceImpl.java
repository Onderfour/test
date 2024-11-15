package com.sd.sdzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sd.sdzx.manager.mapper.OrderInfoMapper;
import com.sd.sdzx.manager.mapper.OrderStatisticsMapper;
import com.sd.sdzx.manager.service.OrderInfoService;
import com.sd.sdzx.model.dto.h5.OrderInfoDto;
import com.sd.sdzx.model.dto.order.OrderStatisticsDto;
import com.sd.sdzx.model.dto.system.SysUserDto;
import com.sd.sdzx.model.entity.order.OrderInfo;
import com.sd.sdzx.model.entity.order.OrderItem;
import com.sd.sdzx.model.entity.order.OrderStatistics;
import com.sd.sdzx.model.entity.pay.PaymentInfo;
import com.sd.sdzx.model.entity.product.Brand;
import com.sd.sdzx.model.entity.user.UserInfo;
import com.sd.sdzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper ;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {

        // 查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto) ;


        //日期列表
        List<String> dateList = orderStatisticsList
                .stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());

        //统计金额列表
        List<BigDecimal> amountList = orderStatisticsList
                .stream()
                .map(OrderStatistics::getTotalAmount)
                .collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo() ;
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        // 返回数据
        return orderStatisticsVo;
    }

//    @Override
//    public PageInfo<OrderInfo> findByPage(OrderInfo orderInfo,Integer page, Integer limit) {
//        PageHelper.startPage(page, limit);
//        List<OrderInfo> orderInfoList = orderInfoMapper.findByPage(orderInfo) ;
//        return new PageInfo(orderInfoList);
//    }
    @Override
    public PageInfo<OrderInfo> findByPage(SysUserDto sysUserDto, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<OrderInfo> orderInfoList = orderInfoMapper.findByPage(sysUserDto) ;
        PageInfo pageInfo = new PageInfo(orderInfoList) ;
        return pageInfo;
    }



    @Override
    public List<OrderInfo> findAll() {
        return orderInfoMapper.findAll();
    }

    @Override
    public OrderInfo getOrderInfoWithDetails(String orderNo, Long orderId) {
        return orderInfoMapper.getOrderInfoWithDetails(orderNo, orderId);
    }
    @Transactional
    @Override
    public void updateOrderInfo(OrderInfo orderInfo) {

        orderInfoMapper.updateByOrderId(orderInfo);
        List<OrderItem> orderItemList = orderInfo.getOrderItemList();
        orderItemList.forEach(orderItem -> {
            orderInfoMapper.updateByItemId(orderItem);
        });
       PaymentInfo paymentInfo = orderInfoMapper.selectByPayId(orderInfo.getOrderNo());
       paymentInfo.setPaymentStatus(orderInfo.getOrderStatus());
       orderInfoMapper.updateByPayId(paymentInfo);

    }


}
