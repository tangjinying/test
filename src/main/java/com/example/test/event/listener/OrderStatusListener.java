package com.example.test.event.listener;

import com.example.test.event.entity.OrderStatus;
import com.example.test.event.entity.OrderStatusEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 */
@Component
public class OrderStatusListener implements ApplicationListener<OrderStatusEvent> {

    @Override
    public void onApplicationEvent(OrderStatusEvent event) {
        System.out.println(event.getSource());
        System.out.println(event.getTimestamp());
        OrderStatus orderStatus = (OrderStatus) event.getSource();
        System.out.println("监听到消息：订单编号为：" + orderStatus.getOrderId() + "的订单状态发生变化。");
    }

}
