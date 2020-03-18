package com.tensquare.base.controller;

import com.tensquare.base.entity.City;
import com.tensquare.base.service.ICityService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@Controller
public class CityController {

    @Resource
    private ICityService cityService;


    /**
     * 新增城市
     */
    @ResponseBody
    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public Result addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    /**
     * 返回城市列表
     */
    @ResponseBody
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public Result getCityList() {
        return cityService.getCityList();
    }

    /**
     * 修改城市
     */
    @ResponseBody
    @RequestMapping(value = "/city/{cityId}", method = RequestMethod.PUT)
    public Result editCity(@RequestBody City city,@PathVariable String cityId) {
        return cityService.editCity(city,cityId);
    }

    /**
     * 删除城市
     */
    @ResponseBody
    @RequestMapping(value = "/city/{cityId}", method = RequestMethod.DELETE)
    public Result deleteCity(@PathVariable String cityId) {
        return cityService.deleteCity(cityId);
    }

    /**
     * 根据ID查询城市
     */
    @ResponseBody
    @RequestMapping(value = "/city/{cityId}", method = RequestMethod.GET)
    public Result getCityById(@PathVariable String cityId) {
        return cityService.getCityById(cityId);
    }

    /**
     * 根据条件查询城市列表
     */
    @ResponseBody
    @RequestMapping(value = "/city/search", method = RequestMethod.POST)
    public Result getCityByParam(@RequestBody City city) {
        return cityService.getCityByParam(city);
    }

    /**
     * 根据条件查询城市列表
     */
    @ResponseBody
    @RequestMapping(value = "/city/search/{pageNo}/{pageSize}", method = RequestMethod.POST)
    public Result getCityByParamWithPage(@PathVariable Integer pageNo,@PathVariable Integer pageSize,@RequestBody City city) {
        return cityService.getCityByParamWithPage(city,pageNo,pageSize);
    }

}
