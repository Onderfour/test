
package com.sd.sdzx.manager;
import com.sd.sdzx.manager.mapper.OrderInfoMapper;
import com.sd.sdzx.model.entity.order.OrderInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@SpringBootTest
public class OderDetailsTest {

    @Autowired(required=true)
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void testGetOrderInfoWithDetails() {
        String orderNo = "1699362108334"; // 替换为实际的订单号
        Long orderId = 218L; // 替换为实际的订单ID
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoWithDetails(orderNo, orderId);
//        assertNotNull(orderInfo);
        System.out.println("------------------");

    }
    @Test
    public void testGetOderPayDetails(){
    OrderInfo orderInfo=new OrderInfo();
    orderInfo.setId(218L);
    orderInfo.setOrderNo("1699362108334");
    orderInfo.setReceiverAddress("北京市北京市东城区");
    orderInfoMapper.updateByOrderId(orderInfo);

    }
    @Test
    void add(){
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张三丰","张嘴山","张无忌","张三","赵四");
        long count = list.stream().filter(s->s.length()==3).skip(2).count();
        System.out.println(count);
    }






}
