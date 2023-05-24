package com.task2group13.service.impl;

import com.task2group13.mapper.ProductMapper;
import com.task2group13.mapper.SupplierMapper;
import com.task2group13.pojo.PageBeanProduct;
import com.task2group13.pojo.Product;
import com.task2group13.pojo.Supplier;
import com.task2group13.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Supplier> getAll() {
        return supplierMapper.getAll();
    }

    @Override
    public void deleteById(Integer id) {
        supplierMapper.deleteById(id);
    }

    @Override
    public Supplier getById(Integer id) {
        return supplierMapper.getById(id);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierMapper.updateSupplier(supplier);
    }

    @Override
    public void insertSupplier(Supplier supplier) {
        supplierMapper.insertSupplier(supplier);
    }

    @Override
    public PageBeanProduct getProductsBySupplierId(Integer id, Integer page, Integer pageSize) {

        Long count = productMapper.countBySupplierId(id);

        Integer start = (page - 1) * pageSize;

        List<Product> productList = productMapper.getBySupplierId(id, start, pageSize);

        return new PageBeanProduct(count, productList);
    }

}
