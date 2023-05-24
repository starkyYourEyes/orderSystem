package com.task2group13.service;

import com.task2group13.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    /**
     * 获取所有产品
     * @return
     */
    List<Product> getAllOnSale();

    /**
     * 按照Id删除产品
     * @param id
     */
    void deleteProduct(Integer id);

    /**
     * 按照id查询产品
     * @param id
     * @return
     */
    Product getProductById(Integer id);

    /**
     * 更新产品
     */
    void updateProductInfo(Product product);

    /**
     * 新增产品
     * @param product
     */
    void addNewProduct(Product product);

    /**
     * 所有产品
     * @return
     */
    List<Product> getAllProducts();
}
