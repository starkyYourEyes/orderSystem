package com.task2group13.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId;
    private String productName;
    private String unitNumber;
    private BigDecimal unitPrice;
    private Short inventory;
    private Boolean suspend;
    private Integer supplierId;
    private Integer typeId;
}
