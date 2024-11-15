package com.sd.sdzx.manager.mapper;

import com.sd.sdzx.model.dto.order.OrderStatisticsDto;
import com.sd.sdzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderStatisticsMapper {
    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
