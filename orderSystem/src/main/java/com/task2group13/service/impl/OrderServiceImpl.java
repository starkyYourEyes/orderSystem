package com.task2group13.service.impl;

import com.task2group13.mapper.OrderMapper;
import com.task2group13.pojo.Order;
import com.task2group13.pojo.PageBeanClient;
import com.task2group13.pojo.PageBeanOrder;
import com.task2group13.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAll() {
        return orderMapper.getAll();
    }

    @Override
    public List<Order> getByClientId(String id) {
        return orderMapper.getByClientId(id);
    }

    @Override
    public List<Order> getByShipperId(Integer id) {
        return orderMapper.getByShipperId(id);
    }

    @Override
    public PageBeanOrder page(Integer page, Integer pageSize) {
        //1、获取总记录数,
        Long count = orderMapper.count();
//        List<Order> all = orderMapper.getOrder();
        Integer start = (page - 1) * pageSize;
        List<Order> orderList = orderMapper.page(start, pageSize);
        //封装PageBean对象
        PageBeanOrder pageBean = new PageBeanOrder(count , orderList);
        return pageBean;

    }

    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.getById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.update(order);
    }
}
