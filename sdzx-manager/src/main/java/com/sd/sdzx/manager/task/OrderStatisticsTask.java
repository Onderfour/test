package com.sd.sdzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.sd.sdzx.manager.mapper.OrderInfoMapper;
import com.sd.sdzx.manager.mapper.OrderStatisticsMapper;
import com.sd.sdzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

//    @Scheduled(cron = "0/10 * * * * ?")
     @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        System.out.println(new Date().toInstant());
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }
    }
}