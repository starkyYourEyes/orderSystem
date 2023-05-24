package com.task2group13.controller;

import com.task2group13.mapper.ClientMapper;
import com.task2group13.pojo.*;
import com.task2group13.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/clients")
@Transactional(rollbackFor = Exception.class)
public class ClientController {

    @Autowired
    private ClientService clientService;

//    @GetMapping
//    public Result getAll(){
//        log.info("查询所有的客户信息");
//        ArrayList<Client> clientList = clientService.getAll();
//        return Result.success(clientList);
//    }


    //条件分页查询
    @GetMapping
    public Result page(String name, String address,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize) {
        //记录日志
        log.info("分页查询，参数：{},{}", page, pageSize);
        //调用业务层分页查询功能
        PageBeanClient pageBean = clientService.page(name, address, page, pageSize);
        //响应
        return Result.success(pageBean);
    }

    @GetMapping("/select")
    public Result getClientsList(String name, String address){
        List<Client> clientList =  clientService.getClientsList(name, address);
        return Result.success(clientList);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id){
        log.info("根据id查询客户信息, id:{}", id);
        Client client = clientService.getById(id);
        return Result.success(client);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        log.info("删除客户:{}", id);
        clientService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Client client){

        if(client.getClientId().length() != 5){
            log.info("客户id长度输入错误: {}", client);
            return Result.error("客户ID必须是长度为5的字符串");
        }
        log.info("添加一个客户：{}", client);
        clientService.add(client);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Client client){
        log.info("修改客户信息:{}", client);
        clientService.update(client);
        return Result.success();
    }


    @PostMapping("/newOrder")
    public Result sendOrder(@RequestBody OrderProducts orderProducts) throws Exception {
        if (orderProducts.getProductNumberList() == null || orderProducts.getProductNumberList().size() == 0){
            throw new Exception("还没有选择商品");
        }
        log.info("用户选择产品并发送订单：{}", orderProducts);
        log.info("新增订单");
        try {
            Integer integer = clientService.sendOrder(orderProducts.getOrder(), orderProducts.getIdp());
            // 执行新增订单后返回了订单的 主键值 orderId
            log.info("返回值:" + integer);
            Integer id = orderProducts.getOrder().getOrderId();
            log.info("修改订单明细数据库：");
            clientService.orderDetails(orderProducts.getProductNumberList(), id);
        } catch (Exception e){
            log.info("商品数量不足！");
            e.printStackTrace();
        }
        return Result.success();
    }

    @PutMapping("/confirm/{ido}")
    public Result confirmOrder(@PathVariable Integer ido){
        log.info("用户确认订单");
        clientService.confirmOrder(ido);
        return Result.success();
    }

    @PutMapping("/updateOrder")
    public Result updateOrderInfo(@RequestBody Order order){
        log.info("用户修改订单信息：{}", order);
        // 规定 这里前端给的 表单 中 只能包含 owner 开头的几个属性
        clientService.updateOrder(order);
        return Result.success();
    }

    @DeleteMapping("/deleteOrder/{ido}")
    public Result deleteOrder(@PathVariable Integer ido){
        log.info("用户撤销订单：{}", ido);
        clientService.deleteOrder(ido);
        return Result.success();
    }

    @GetMapping("/orders")
    public Result getOrders(String id,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查看用户{}的所有订单，分页查询参数,{},{}", id, page, pageSize);
        PageBeanOrder orderList = clientService.getOrders(id, page, pageSize);
        return Result.success(orderList);
    }

    @GetMapping("/orderDetails/{id}")
    public Result getOrderDetails(@PathVariable Integer id){    // 这里的id是订单id
        log.info("获取订单的订单详情:{}", id);
        List<OrderDetails> orderDetailsList = clientService.getOrderDetails(id);
        return Result.success(orderDetailsList);
    }
}

