package com.task2group13.service.impl;

import com.task2group13.mapper.ProductMapper;
import com.task2group13.pojo.Product;
import com.task2group13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllOnSale() {
        return productMapper.getAllOnSale();
    }

    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }

    @Override
    public void updateProductInfo(Product product) {
        productMapper.updateProductInfo(product);
    }

    @Override
    public void addNewProduct(Product product) {
        // 新增的产品不能直接停售
        product.setSuspend(false);
        productMapper.insertNewProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }
}
