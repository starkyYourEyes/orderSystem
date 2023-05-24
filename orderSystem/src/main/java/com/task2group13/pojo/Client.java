package com.task2group13.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String clientId;
    private String companyName;
    private String linkmanName;
    private String linkmanPos;
    private String address;
    private String city;
    private String zone;
    private String postcode;
    private String country;
    private String phone;
    private String fax;
}
