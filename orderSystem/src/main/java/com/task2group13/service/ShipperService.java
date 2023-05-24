package com.task2group13.service;

import com.task2group13.pojo.Order;
import com.task2group13.pojo.PageBeanOrder;
import com.task2group13.pojo.Shipper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface ShipperService {

    /**
     * 根据id查询shipper
     * @return
     */
    public List<Shipper> getAll();

    /**
     * 运货商为某一订单运货
     * @param ids
     * @param ido
     */
//    void ship(String str);
    void ship(Integer ids, Integer ido);

    /**
     * 查询待运输的订单
     *
     * @return
     */
    PageBeanOrder getShippableOrders(Integer page, Integer pageSize);

    /**
     * 订单到货
     * @param ido
     */
    void arrive(Integer ido);

    /**
     * 删除运货商
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增shipper
     * @param shipper
     */
    void insert(Shipper shipper);

    /**
     * 根据Id查询运货商
     * @param id
     * @return
     */
    Shipper getById(Integer id);

    /**
     * 编辑运货商
     * @param shipper
     */
    void updateShipper(Shipper shipper);

    /**
     * 查看某个运货商运送的还没有到货的订单
     * @param id
     * @return
     */
    List<Order> getNotArrivedOrdersByShipperId(Integer id);
}
