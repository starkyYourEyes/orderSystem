package com.task2group13.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private LocalDateTime buyDate;
    private LocalDateTime deliverDate;
    private LocalDateTime arriveDate;
    private LocalDateTime confirmDate;
    private String ownerName;
    private String ownerAddress;
    private String ownerCity;
    private String ownerZone;
    private String ownerPostcode;
    private String ownerCountry;
    private String clientId;
    private Integer shipperId;
    private BigDecimal totalCost;
}
