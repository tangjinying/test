package com.example.test.event.service;

import com.example.test.event.entity.OrderStatus;
import com.example.test.event.entity.OrderStatusEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void orderStatusChange() {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId("123");
        orderStatus.setStatus("1");
        OrderStatusEvent orderStatusEvent = new OrderStatusEvent(orderStatus);
        applicationEventPublisher.publishEvent(orderStatusEvent);
    }

}
