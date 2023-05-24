package com.task2group13.mapper;

import com.task2group13.pojo.Client;
import com.task2group13.pojo.Login;
import com.task2group13.pojo.Order;
import com.task2group13.pojo.OrderDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ClientMapper {
    /**
     * 查询所有的客户信息
     * @return
     */
    @Select("select * from orderSystem.client")
    ArrayList<Client> getAll();

    /**
     * 根据Id查询客户
     * @return
     */
    @Select("select * from orderSystem.client where client_id = #{id}")
    Client getById(String id);

    /**
     * 删除客户
     */
    @Delete("delete from orderSystem.client where client_id = #{id}")
    void delete(String id);

    /**
     * 新增客户
     * @param client
     */
    void add(Client client);

    /**
     * 修改客户信息
     * @param client
     */
    void update(Client client);

    /**
     * 获取总的客户数量
     * @return
     */
//    @Select("select count(*) from orderSystem.client")
    Long count(String name, String address);

    /**
     * 分页查询的结果
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select top ${pageSize} client_id, company_name, linkman_name, linkman_pos, address, city, zone, postcode, country, phone, fax from orderSystem.client where client_id not in (select top ${start} client_id from orderSystem.client)")
    List<Client> list(String name, String address, Integer start, Integer pageSize);

    @Select("select top ${pageSize} * from orderSystem.[order] where client_id = #{id} and order_id not in (select top ${start} order_id from orderSystem.[order] where client_id = #{id} order by confirm_date, arrive_date desc ,deliver_date) order by confirm_date, arrive_date desc ,deliver_date")
    List<Order> getOrdersByClientId(String id, Integer start, Integer pageSize);

    @Select("select count(*) from orderSystem.[order] where client_id = #{id}")
    Long getAllCount(String id);

    @Select("select * from orderSystem.order_details where order_id = #{id}")
    List<OrderDetails> getOrderDetails(Integer id);

//    @Select("select * from orderSystem.client where linkman_name like '%' + #{name} and address like '%' + #{address}")
    List<Client> getClientsList(String name, String address);

    @Select("select count(*) from orderSystem.admin where account = #{account} and password = #{password}")
    Integer login(Login login);
}
