package com.task2group13.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.task2group13.pojo.Order;
import com.task2group13.pojo.PageBeanOrder;
import com.task2group13.pojo.Result;
import com.task2group13.pojo.Shipper;
import com.task2group13.service.ShipperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/shippers")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping
    public Result getAll(){
        log.info("查询shippers");
        List<Shipper> shipper = shipperService.getAll();
        return Result.success(shipper);
    }

    @PostMapping
    public Result insertShipper(@RequestBody Shipper shipper){
        log.info("新增运货商");
        shipperService.insert(shipper);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Shipper shipper = shipperService.getById(id);
        return Result.success(shipper);
    }

    @PutMapping
    public Result updateShipper(@RequestBody Shipper shipper){
        log.info("编辑运货商{}", shipper);
        shipperService.updateShipper(shipper);
        return Result.success();
    }



    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("按id删除运货商, {}", id);
        shipperService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/newOrder")
    public Result getOrder(Integer ids, Integer ido){
        log.info("运货商收到订单，并为订单运货:");
        shipperService.ship(ids, ido);
        return Result.success();
    }

//    @GetMapping("/shippableOrders")
//    public Result getShippableOrders(){
//        log.info("查看当前还没被运送的订单");
//        PageBeanOrder orderList = shipperService.getShippableOrders();
//        return Result.success(orderList);
//    }

    @GetMapping("/shippableOrders")
    public Result getShippableOrders(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查看当前还没被运送的订单，分页查询参数,{},{}", page, pageSize);
        PageBeanOrder orderList = shipperService.getShippableOrders(page, pageSize);
        return Result.success(orderList);
    }

    @GetMapping("/notArrivedOrders/{id}")
    public Result notArrivedOrders(@PathVariable Integer id){
        log.info("运货商{}, 查看正在运输的订单", id);
        List<Order> orderList = shipperService.getNotArrivedOrdersByShipperId(id);

        return Result.success(orderList);
    }

    @PutMapping("/arrive/{ido}")
    public Result arrive(@PathVariable Integer ido){
        log.info("{}订单到货了:", ido);
        shipperService.arrive(ido);
        return Result.success();
    }


}
