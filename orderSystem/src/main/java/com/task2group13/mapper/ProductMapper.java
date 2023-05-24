package com.task2group13.mapper;

import com.task2group13.pojo.Product;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 更新数量
     * @param quantity
     * @param id
     */
    @Update("update orderSystem.product set inventory = inventory - #{quantity} where product_id = #{id}")
    void update(Short quantity, Integer id);

    /**
     * 查询某一个商品
     * @param productId
     * @return
     */
    @Select("select * from orderSystem.product where product_id = #{productId}")
    Product getById(Integer productId);

    @Select("select * from orderSystem.product where suspend = 0 and inventory is not null")
    List<Product> getAllOnSale();

//    @Select("select count(*) from orderSystem.product where supplier_id = #{id}")
    Long countBySupplierId(Integer id);

//    @Select("select top ${pageSize} * from orderSystem.product where supplier_id = #{id} and supplier_id not in (select top ${start} supplier_id from orderSystem.product where supplier_id = #{id})")
    List<Product> getBySupplierId(Integer id, Integer start, Integer pageSize);


    @Delete("delete from orderSystem.product where product_id = #{id}")
    void deleteProduct(Integer id);

    @Select("select * from orderSystem.product where product_id = #{id}")
    Product getProductById(Integer id);

    void updateProductInfo(Product product);

    void insertNewProduct(Product product);

    @Select("select * from orderSystem.product")
    List<Product> getAllProducts();
}
