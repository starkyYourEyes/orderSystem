package com.task2group13.controller;

import com.task2group13.pojo.Product;
import com.task2group13.pojo.ProductNumber;
import com.task2group13.pojo.Result;
import com.task2group13.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Result getAllOnSale(){
        log.info("查询所有的在售的商品");
        List<Product> productList = productService.getAllOnSale();
        return Result.success(productList);
    }

    @GetMapping("/all")
    public Result getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        log.info("查看所有的产品");
        return Result.success(productList);
    }

    @GetMapping("/{id}")
    public Result getProductById(@PathVariable Integer id){
        log.info("根据ID查询产品, {}", id);
        Product product = productService.getProductById(id);
        return Result.success(product);
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        log.info("删除产品， {}", id);
        productService.deleteProduct(id);
        return Result.success();
    }

    @PutMapping
    public Result updateProductInfo(@RequestBody Product product){
        log.info("更新产品, {}", product);
        productService.updateProductInfo(product);
        return Result.success();
    }

    @PostMapping
    public Result addNerProduct(@RequestBody Product product){
        log.info("新增一个产品, {}", product);
        productService.addNewProduct(product);
        return Result.success();
    }

}
