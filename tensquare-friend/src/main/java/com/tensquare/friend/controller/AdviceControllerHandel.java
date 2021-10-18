package com.tensquare.friend.controller;

import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class AdviceControllerHandel {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.info("系统异常，请联系管理员",e);
        return new Result(ResultEnum.ERROR);
    }

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public Result exception(NullPointerException e){
        log.info("有数据为Null，请联系管理员",e);
        return new Result(ResultEnum.NULL_PARAM);
    }
}


