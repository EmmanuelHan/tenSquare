package com.tensquare.user.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("login")
    public Result login(){
        return new Result(ResultEnum.LOGIN);
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
