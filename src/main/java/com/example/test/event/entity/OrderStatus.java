package com.example.test.event.entity;

import java.io.Serializable;

/**
 * @Description
 */
public class OrderStatus implements Serializable {
    private static final long serialVersionUID = -6629708962653006905L;

    private String orderId;

    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
