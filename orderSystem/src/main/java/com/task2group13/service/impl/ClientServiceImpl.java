package com.task2group13.service.impl;

import com.task2group13.mapper.ClientMapper;
import com.task2group13.mapper.OrderMapper;
import com.task2group13.mapper.ProductMapper;
import com.task2group13.pojo.*;
import com.task2group13.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ArrayList<Client> getAll() {
        return clientMapper.getAll();
    }

    @Override
    public Client getById(String id) {
        return clientMapper.getById(id);
    }

    @Override
    public void delete(String id) {
        clientMapper.delete(id);
    }

    @Override
    public void add(Client client) {
        clientMapper.add(client);
    }

    @Override
    public void update(Client client) {
        clientMapper.update(client);
    }

    @Override
    public Integer sendOrder(Order order, String idp) {
        // 为订单增加 客户 Id
        order.setClientId(idp);
        // 设置下单时间
        order.setBuyDate(LocalDateTime.now());

        return orderMapper.addOrder(order);
    }

    @Override
    public void orderDetails(List<ProductNumber> products, Integer orderId) throws Exception {
        // 修改product的库存
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (ProductNumber product : products) {
            // 查找这个商品，确定库存
            Product pro = productMapper.getById(product.getProductId());
            if (pro.getSuspend()){
                throw new Exception("商品暂停供货");
            }
            if (pro.getInventory() < product.getQuantity()){
                throw new Exception("库存量不足, 当前仅剩" + pro.getInventory());
            } else{
                //另外还需要减少商品的库存
                productMapper.update(product.getQuantity(), product.getProductId());
            }
            BigDecimal bigDecimal = BigDecimal.valueOf(0);
            orderDetailsList.add(new OrderDetails(orderId, product.getProductId(), product.getQuantity(), bigDecimal));
        }
        // 添加订单明细
        orderMapper.orderDetails(orderDetailsList);
    }

    @Override
    public PageBeanOrder getOrders(String id, Integer page, Integer pageSize) {

        Long count = clientMapper.getAllCount(id);

        Integer start = (page - 1) * pageSize;
        List<Order> ordersByClientId = clientMapper.getOrdersByClientId(id, start, pageSize);
        return new PageBeanOrder(count, ordersByClientId);
    }

//    @Override
//    public List<Order> getOrders(String id) {
//        return orderMapper.getClientOrders(id);
//    }

    @Override
    public void confirmOrder(Integer ido) {
        LocalDateTime time = LocalDateTime.now();
        orderMapper.confirmOrder(ido, time);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void deleteOrder(Integer ido) {
        orderMapper.delete(ido);
    }

    @Override
    public PageBeanClient page(String name, String address, Integer page, Integer pageSize) {
        //1、获取总记录数
        Long count = clientMapper.count(name, address);

        //2、获取分页查询结果列表
//        Integer start = (page - 1) * pageSize; //计算起始索引 , 公式: (页码-1)*页大小
        Integer start = (page - 1) * pageSize;
        List<Client> clientList = clientMapper.list(name, address, start, pageSize);

        //3、封装PageBean对象
        PageBeanClient pageBean = new PageBeanClient(count , clientList);
        return pageBean;
    }

    @Override
    public List<OrderDetails> getOrderDetails(Integer id) {
        return clientMapper.getOrderDetails(id);
    }

    @Override
    public List<Client> getClientsList(String name, String address) {
        return clientMapper.getClientsList(name, address);
    }

    @Override
    public Integer login(Login login) {
        return clientMapper.login(login);
    }

}
