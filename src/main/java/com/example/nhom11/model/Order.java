package com.example.nhom11.model;

import java.util.Date;

public class Order {
    private long id;
    private Cart cart;
    private Date date;
    private ReceivingType receivingType;
    private Shipment shipment;
    private String description; //Mô tả (Chức năng này không làm, chỉ để cho đẹp)
    private float gia;  //Tổng giá của đơn hàng (Giá của các sản phẩm + Giá vận chuyển - Khuyến mãi nếu có)

    public Order() {
    }



}
