package com.tensquare.qa.feign;

import com.tensquare.qa.feign.impl.LableFeignImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "tensquare-base",fallback = LableFeignImpl.class)
public interface ILableFeign {

    /**
     * 标签全部列表
     */
    @GetMapping("/label")
    Result getLabelList();

}
