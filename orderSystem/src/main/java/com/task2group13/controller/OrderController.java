package com.task2group13.controller;

import com.task2group13.pojo.Order;
import com.task2group13.pojo.PageBeanClient;
import com.task2group13.pojo.PageBeanOrder;
import com.task2group13.pojo.Result;
import com.task2group13.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @GetMapping
//    public Result getByClientId(@RequestParam(defaultValue = "1") Integer page,
//                                @RequestParam(defaultValue = "10") Integer pageSize,
//                                String clientId, Integer shipperId){
//        // begin表示从何时起
//        log.info("分页查询，参数:{},{},{},{}", page, pageSize, clientId, shipperId);
////        List<Order> orderList = orderService.getByClientId(id);
//        PageBean pageBean = orderService.page(page, pageSize, clientId, shipperId);
//        return Result.success(pageBean);
//    }

//    @RequestMapping
//    public Result getAll(){
//        log.info("查询所有订单");
//        List<Order> orderList = orderService.getAll();
//        return Result.success(orderList);
//    }

    //条件分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        //记录日志
        log.info("分页查询，参数：{},{}", page, pageSize);
        //调用业务层分页查询功能
        PageBeanOrder pageBean = orderService.page(page, pageSize);
        // 响应， 返回数据给前端
        return Result.success(pageBean);
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询订单，{}", id);
        Order order = orderService.getById(id);
        return Result.success(order);
    }

    @PutMapping
    public Result updateOrder(@RequestBody Order order){
        log.info("修改订单信息:{}", order);
        orderService.updateOrder(order);
        return Result.success();
    }

    @PostMapping
    public Result addOrder(@RequestBody Order order){
        log.info("新增一个订单:{}", order);
        orderService.addOrder(order);

        return Result.success();
    }

}
