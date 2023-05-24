package com.task2group13.controller;

import com.task2group13.pojo.PageBeanProduct;
import com.task2group13.pojo.Product;
import com.task2group13.pojo.Result;
import com.task2group13.pojo.Supplier;
import com.task2group13.service.SupplierService;
import com.task2group13.service.impl.SupplierServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public Result getAll(){
        log.info("查询所有的供应商");
        List<Supplier> list = supplierService.getAll();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询供应商, {}", id);
        Supplier supplier = supplierService.getById(id);
        return Result.success(supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("删除供应商, {}", id);
        supplierService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result updateSupplier(@RequestBody Supplier supplier){
        log.info("更新供应商信息, {}", supplier);
        supplierService.updateSupplier(supplier);
        return Result.success();
    }

    @PostMapping
    public Result insertSupplier(@RequestBody Supplier supplier){
        log.info("新增一个供应商, {}", supplier);
        supplierService.insertSupplier(supplier);
        return Result.success();
    }

    @GetMapping("/products")
    public Result getProducts(Integer id,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查看某一个供应商{}的产品信息", id);
        PageBeanProduct productList = supplierService.getProductsBySupplierId(id, page, pageSize);
        return Result.success(productList);
    }

}
