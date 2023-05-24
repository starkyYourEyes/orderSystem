package com.task2group13.service.impl;

import com.task2group13.mapper.TypeMapper;
import com.task2group13.pojo.Type;
import com.task2group13.service.TypeService;
import com.task2group13.utils.FileToByteArray;
import com.task2group13.utils.MultipartFileToFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    public void saveImage(byte[] imageData) {
        typeMapper.insertImage(imageData);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.getAllTypes();
    }

    @Override
    public void addNewType(Type type, File file) {
        // 插入图片！
        // 读取转化为字节数组，然后就操作字节数组，
//            byte[] image = Files.readAllBytes(file.toPath());
        byte[] image = FileToByteArray.fileToByte(file);
        type.setImage(image);
        typeMapper.addNewType(type);
        MultipartFileToFile.delteTempFile(file);
    }

    @Override
    public void deleteType(Integer id) {
        typeMapper.deleteType(id);
    }

    @Override
    public Type getByTypeId(Integer id) {
        return typeMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateType(Type type, File file) {
        // 插入图片！
        try {
            // 读取转化为字节数组，然后就操作字节数组，
            byte[] image = Files.readAllBytes(file.toPath());
            type.setImage(image);
            typeMapper.updateTypeInfo(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
