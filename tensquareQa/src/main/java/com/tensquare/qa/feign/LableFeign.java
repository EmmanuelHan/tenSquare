package com.tensquare.qa.feign;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("tensquare-base")
public interface LableFeign {


    /**
     * 标签全部列表
     */
    @GetMapping("/label")
    Result getLabelList();

}
