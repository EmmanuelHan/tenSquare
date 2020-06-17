package com.tensquare.base.controller;

import com.tensquare.base.entity.City;
import com.tensquare.base.service.ICityService;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@CrossOrigin
@RestController
public class CityController {

    @Resource
    private ICityService cityService;

    /**
     * 新增城市
     */
    @PostMapping("/city")
    public Result addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    /**
     * 返回城市列表
     */
    @GetMapping("/city")
    public Result getCityList() {
        return cityService.getCityList();
    }

    /**
     * 修改城市
     */
    @PutMapping("/city/{cityId}")
    public Result editCity(@RequestBody City city,@PathVariable String cityId) {
        return cityService.editCity(city,cityId);
    }

    /**
     * 删除城市
     */
    @DeleteMapping("/city/{cityId}")
    public Result deleteCity(@PathVariable String cityId) {
        return cityService.deleteCity(cityId);
    }

    /**
     * 根据ID查询城市
     */
    @GetMapping("/city/{cityId}")
    public Result getCityById(@PathVariable String cityId) {
        return cityService.getCityById(cityId);
    }

    /**
     * 根据条件查询城市列表
     */
    @PostMapping("/city/search")
    public Result getCityByParam(@RequestBody City city) {
        return cityService.getCityByParam(city);
    }

    /**
     * 根据条件查询城市列表
     */
    @PostMapping("/city/search/{pageNo}/{pageSize}")
    public Result getCityByParamWithPage(@PathVariable Integer pageNo,@PathVariable Integer pageSize,@RequestBody City city) {
        return cityService.getCityByParamWithPage(city,pageNo,pageSize);
    }

}
