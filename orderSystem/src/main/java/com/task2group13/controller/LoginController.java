package com.task2group13.controller;

import com.task2group13.pojo.Login;
import com.task2group13.pojo.Result;
import com.task2group13.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class LoginController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public Result login(@RequestBody Login login){
        log.info("用户登陆了,{}", login);
        Integer integer = clientService.login(login);
        return Result.success(integer);
    }
}
