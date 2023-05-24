package com.task2group13.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipper {
    private Integer shipperId;
    private String companyName;
    private String phone;
}
