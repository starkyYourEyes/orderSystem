package com.task2group13.controller;

import com.task2group13.pojo.Result;
import com.task2group13.pojo.Type;
import com.task2group13.service.TypeService;
import com.task2group13.utils.MultipartFileToFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    // 暂存图片
    private File image;

    @GetMapping
    public Result getAllTypes(){
        log.info("查看所有的产品类别");
        List<Type> typeList = typeService.getAllTypes();

        return Result.success(typeList);
    }

    @GetMapping("/{id}")
    public Result getByTypeId(@PathVariable Integer id){
        log.info("根据ID查询类别, {}", id);
        Type type = typeService.getByTypeId(id);
        return Result.success(type);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        log.info("获取某一个类别的图片信息, {}", id);
        byte[] imageBytes = typeService.getAllTypes().get(id).getImage();

        // 设置HTTP响应头，告诉浏览器返回的是图片数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 根据实际图片类型设置MediaType

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Result deleteType(@PathVariable Integer id){
        log.info("删除一个类别, {}", id);
        typeService.deleteType(id);
        return Result.success();
    }

    @PostMapping
    public Result addNewType(@RequestBody Type type){
        // 图片
        log.info("新增类别信息, {}", type.getTypeName());
        typeService.addNewType(type, image);
        return Result.success();
    }

    @PostMapping("/imageUpload")
    public void insertOneImageFile(HttpServletRequest request, HttpServletRequest response, HttpSession session) throws Exception {
        log.info("上传了一张图片");
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        assert multipartFile != null;
        this.image = MultipartFileToFile.multipartFileToFile(multipartFile);

    }
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Result updateTypeInfo(@RequestBody Type type) {
        log.info("更新类别信息， {}", type);
        typeService.updateType(type, image);
        return Result.success();
    }
}
