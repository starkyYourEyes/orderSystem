package com.task2group13.exception;

import com.task2group13.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)  // 捕获所有异常
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系客服。");
    }
}
