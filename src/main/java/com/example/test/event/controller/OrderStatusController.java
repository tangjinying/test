package com.example.test.event.controller;

import com.example.test.event.service.OrderStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 */
@RestController
public class OrderStatusController {

    @Resource
    private OrderStatusService orderStatusService;

    @GetMapping("/orderStatusChange")
    public void orderStatusChange() {
        orderStatusService.orderStatusChange();
    }

}
