package com.task2group13.service;

import com.task2group13.pojo.Order;
import com.task2group13.pojo.PageBeanOrder;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    /**
     * 查询所有的订单信息
     * @return
     */
    List<Order> getAll();

    /**
     * 根据客户ID查询订单
     * @param id
     * @return
     */
    List<Order> getByClientId(String id);
    /**
     * 根据运货商ID查询订单
     * @param id
     * @return
     */
    List<Order> getByShipperId(Integer id);

    /**
     * 分页查询订单
     */
    PageBeanOrder page(Integer page, Integer pageSize);

    /**
     * 新增订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 根据Id查询订单
     * @param id
     * @return
     */
    Order getById(Integer id);

    /**
     * 修改order
     * @param order
     */
    void updateOrder(Order order);
}
