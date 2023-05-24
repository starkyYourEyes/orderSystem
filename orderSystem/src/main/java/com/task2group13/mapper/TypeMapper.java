package com.task2group13.mapper;

import com.task2group13.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

@Mapper
public interface TypeMapper {

//    @Select("select image from orderSystem.type where type_id = 2")
//    public InputStream getImage();

//    @Select("select cast(image as varbinary(MAX)) as imageName from orderSystem.type where type_id = 2")
//    public byte[] getImage();
    @Select("SELECT type_id, type_name, instruction, image FROM orderSystem.type WHERE type_id = #{id}")
//    @Select("SELECT type_id, type_name, instruction, image FROM orderSystem.type WHERE type_id = #{id}")
    public Type getImage(Integer id);

    @Select("select * from orderSystem.type where type_id = #{i}")
    Type getType(int i);


    @Insert("INSERT INTO orderSystem.type VALUES ('1', '2', #{imageData})")
    void insertImage(@Param("imageData") byte[] imageData);

    @Select("select * from orderSystem.type")
    List<Type> getAllTypes();

    void addNewType(Type type);

    @Delete("delete from orderSystem.type where type_id = #{id}")
    void deleteType(Integer id);

    @Select("select * from orderSystem.type where type_id = #{id}")
    Type getById(Integer id);

    void updateTypeInfo(Type type);
}
