package com.task2group13.mapper;

import com.task2group13.pojo.Order;
import com.task2group13.pojo.Shipper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShipperMapper {

    @Select("select * from orderSystem.shipper")
    public List<Shipper> getAll();

    @Delete("delete from orderSystem.shipper where shipper_id = #{id}")
    void deleteById(Integer id);

    void insert(Shipper shipper);

    @Select("select * from orderSystem.shipper where shipper_id = #{id}")
    Shipper getById(Integer id);

    void updateShipper(Shipper shipper);

    @Select("select * from orderSystem.[order] where shipper_id = #{id} and arrive_date is null")
    List<Order> getNotArrivedOrdersByShipperId(Integer id);

}
