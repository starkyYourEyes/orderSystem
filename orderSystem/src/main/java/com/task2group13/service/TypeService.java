package com.task2group13.service;

import com.task2group13.pojo.Type;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public interface TypeService {
    List<Type> getAllTypes();

    /**
     * 增加新类别
     * @param type
     */
    void addNewType(Type type, File file);

    /**
     * 删除类别
     */
    void deleteType(Integer id);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    Type getByTypeId(Integer id);

    /**
     * 更新类别信息
     * @param type
     */
    void updateType(Type type, File file);
}
