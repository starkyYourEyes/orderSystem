package com.task2group13.mapper;

import com.task2group13.pojo.Order;
import com.task2group13.pojo.OrderDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 查询所有订单
     */
    @Select("select * from orderSystem.[order]")
    List<Order> getAll();

    /**
     * 根据客户ID查询订单
     * @param id
     */
    @Select("select * from orderSystem.[order] where client_id = #{id} order by arrive_date desc ,deliver_date")
    List<Order> getByClientId(String id);

    /**
     * 根据运货商ID查询订单
     * @param id
     */
    @Select("select * from orderSystem.[order] where shipper_id = #{id}")
    List<Order> getByShipperId(Integer id);

    // 动态SQL。XML,,这里sql是写在了另一个文件里面
    List<Order> page(Integer start, Integer pageSize);

    /**
     * 新增订单
     * @param order
     */
    Integer addOrder(Order order);

    /**
     * 修改订单
     * @param order
     */
    void update(Order order);

    /**
     * 向order_details中加入数据
     * @param orderDetailsList
     */
    void orderDetails(List<OrderDetails> orderDetailsList);

    /**
     * 查询用户丶所有订单
     * @param id
     */
    @Select("select * from orderSystem.[order] where client_id = #{id} order by arrive_date desc ,deliver_date")
    List<Order> getClientOrders(String id);

    /**
     *
     * 查询 shipper_id 为 null 的订单，
     */
    @Select("select top ${pageSize} * from orderSystem.[order] where shipper_id is null and order_id not in (select top ${start} order_id from orderSystem.[order] where shipper_id is null) ")
    List<Order> getShippableOrders(Integer start, Integer pageSize);

    /**
     * 用户确认订单
     */
    @Update("update orderSystem.[order] set confirm_date = #{time} where order_id = #{ido}")
    void confirmOrder(Integer ido, LocalDateTime time);

    /**
     * 删除订单
     */
    @Delete("delete from orderSystem.[order] where order_id = #{id}")
    void delete(Integer ido);

    void orderArrive(Integer ido, LocalDateTime time);

    @Select("select count(*) from orderSystem.[order]")
    Long count();

    @Select("select count(*) from orderSystem.[order] where shipper_id is null")
    long getShippableCount();

    @Select("select * from orderSystem.[order] where order_id = #{id}")
    Order getById(Integer id);
}
