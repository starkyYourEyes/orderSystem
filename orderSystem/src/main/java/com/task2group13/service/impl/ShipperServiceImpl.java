package com.task2group13.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.task2group13.mapper.OrderMapper;
import com.task2group13.mapper.ShipperMapper;
import com.task2group13.pojo.Order;
import com.task2group13.pojo.PageBeanOrder;
import com.task2group13.pojo.Shipper;
import com.task2group13.service.ShipperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShipperServiceImpl implements ShipperService {
    @Autowired
    private ShipperMapper shipperMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Shipper> getAll() {
        return shipperMapper.getAll();
    }

//    @Override
//    public void ship(String str) {
//        // 解析 json 数据
//        JSONObject jsonObject = JSONObject.parseObject(str);
//        Integer ids = jsonObject.getInteger("ids");
//        JSONArray idos = jsonObject.getJSONArray("ido");
//        List<Integer> ido = new ArrayList<>();
//
//        for (Object obj : idos)
//            ido.add((Integer) obj);
//        log.info("运货商号{}， 订单编号{}", ids, ido);
//
//        for (Integer id : ido) {
//            Order order = new Order();
//            order.setShipperId(ids);
//            order.setOrderId(id);
//            orderMapper.update(order);
//        }
//
//    }

    @Override
    public void ship(Integer ids, Integer ido) {

        log.info("运货商号{}， 订单编号{}", ids, ido);

        Order order = new Order();
        order.setShipperId(ids);
        order.setOrderId(ido);
        order.setDeliverDate(LocalDateTime.now());
        orderMapper.update(order);


    }

    @Override
    public PageBeanOrder getShippableOrders(Integer page, Integer pageSize) {

        long count;
        count = orderMapper.getShippableCount();

        int start = (page - 1) * pageSize;

        List<Order> orderList = orderMapper.getShippableOrders(start, pageSize);

        return new PageBeanOrder(count, orderList);
    }

    @Override
    public void arrive(Integer ido) {

        LocalDateTime time =  LocalDateTime.now();
        orderMapper.orderArrive(ido, time);
    }

    @Override
    public void deleteById(Integer id) {
        shipperMapper.deleteById(id);
    }

    @Override
    public void insert(Shipper shipper) {
        shipperMapper.insert(shipper);
    }

    @Override
    public Shipper getById(Integer id) {
        return shipperMapper.getById(id);
    }

    @Override
    public void updateShipper(Shipper shipper) {
        shipperMapper.updateShipper(shipper);
    }

    @Override
    public List<Order> getNotArrivedOrdersByShipperId(Integer id) {
        return shipperMapper.getNotArrivedOrdersByShipperId(id);
    }
}
