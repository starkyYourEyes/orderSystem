package com.task2group13.service;

import com.task2group13.pojo.PageBeanProduct;
import com.task2group13.pojo.Product;
import com.task2group13.pojo.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SupplierService {

    List<Supplier> getAll();

    void deleteById(Integer id);

    /**
     * 按照id查询supplier
     * @param id
     * @return
     */
    Supplier getById(Integer id);

    /**
     * 编辑 supplier
     * @param supplier
     */
    void updateSupplier(Supplier supplier);

    /**
     * 新增supplier
     * @param supplier
     */
    void insertSupplier(Supplier supplier);

    /**
     * 根据供货商分页查询 产品
     * @param id
     * @param page
     * @param pageSize
     * @return
     */
    PageBeanProduct getProductsBySupplierId(Integer id, Integer page, Integer pageSize);
}
