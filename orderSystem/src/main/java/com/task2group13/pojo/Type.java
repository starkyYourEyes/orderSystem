package com.task2group13.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Integer typeId;
    private String typeName;
    private String instruction;
    private byte[] image;   // 图片类型
}
