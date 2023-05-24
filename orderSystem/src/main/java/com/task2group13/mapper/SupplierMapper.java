package com.task2group13.mapper;


import com.task2group13.pojo.Supplier;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SupplierMapper {

    @Select("select * from orderSystem.supplier")
    List<Supplier> getAll();

    @Delete("delete from orderSystem.supplier where supplier_id = #{id}")
    void deleteById(Integer id);

    @Select("select * from orderSystem.supplier where supplier_id = #{id}")
    Supplier getById(Integer id);

    void updateSupplier(Supplier supplier);

    void insertSupplier(Supplier supplier);
}
