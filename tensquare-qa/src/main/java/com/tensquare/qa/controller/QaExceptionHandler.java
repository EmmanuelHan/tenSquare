package com.tensquare.qa.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tensquare.common.util.StringUtil;

/**
 * @author EmmanuelHan
 */
@Slf4j
@ControllerAdvice
public class QaExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        log.info("全局系统异常，请联系管理员 {}", StringUtil.getException(e));
        return new Result(ResultEnum.ERROR);
    }
}
