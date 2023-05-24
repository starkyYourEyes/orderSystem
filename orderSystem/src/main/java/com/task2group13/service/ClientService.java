package com.task2group13.service;

import com.task2group13.pojo.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface ClientService {
    /**
     * 查询所有客户
     * @return
     */
    ArrayList<Client> getAll();

    /**
     * 根据ID查询客户
     * @return
     */
    Client getById(String id);

    /**
     * 删除客户
     */
    void delete(String id);

    /**
     * 添加客户
     * @param client
     */
    void add(Client client);

    /**
     * 修改客户信息
     * @param client
     */
    void update(Client client);

    /**
     * 客户新增订单
     * @param order
     */
    Integer sendOrder(Order order, String idp);

    /**
     * 客户选择商品, 修改order_details数据库
     * @param products
     * @return
     */
    void orderDetails(List<ProductNumber> products, Integer orderId) throws Exception;

    /**
     * 查看用户的所有订单
     * @param id
     * @return
     */
    PageBeanOrder getOrders(String id, Integer page, Integer pageSize);

    /**
     * 用户确认订单
     *
     * @param ido
     */
    void confirmOrder(Integer ido);

    /**
     * 用户修改 订单信息
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 用户删除订单
     * @param ido
     */
    void deleteOrder(Integer ido);

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageBeanClient page(String name, String address,Integer page, Integer pageSize);

    /**
     * 查询某一个订单号的 订单详情
     * @param id
     * @return
     */
    List<OrderDetails> getOrderDetails(Integer id);

    /**
     * 条件查询
     * @param name
     * @param address
     * @return
     */
    List<Client> getClientsList(String name, String address);

    /**
     * 登录
     * @param login
     * @return
     */
    Integer login(Login login);
}
