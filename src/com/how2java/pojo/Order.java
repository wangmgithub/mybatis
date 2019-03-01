package com.how2java.pojo;

import java.util.List;

/**
 * @author:Mr.wang
 * @date:2019/2/27 0027 上午 9:23
 */
public class Order {
    private int id;
    private String code;
    List<OrderItem> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
