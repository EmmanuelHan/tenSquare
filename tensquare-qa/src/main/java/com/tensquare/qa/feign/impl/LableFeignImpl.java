package com.tensquare.qa.feign.impl;

import com.tensquare.qa.feign.ILableFeign;
import com.tensquare.common.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class LableFeignImpl implements ILableFeign {

    @Override
    public Result getLabelList() {

        return null;
    }
}
