package com.task2group13.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private Integer orderId;
    private Integer productId;
    private Short quantity;
    private BigDecimal cost;
}
