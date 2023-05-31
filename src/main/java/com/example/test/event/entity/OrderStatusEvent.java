package com.example.test.event.entity;

import org.springframework.context.ApplicationEvent;

/**
 * @Description
 */
public class OrderStatusEvent extends ApplicationEvent {

    public OrderStatusEvent(OrderStatus orderStatus) {
        super(orderStatus);
    }
}
