package com.tensquare.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.base.entity.City;
import com.tensquare.common.entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface ICityService extends IService<City> {

    /**
     * 新增城市
     *
     * @param city
     * @return
     */
    Result addCity(City city);

    /**
     * 返回城市列表
     * @return
     */
    Result getCityList();

    /**
     * 修改城市
     * @param city
     * @param cityId
     * @return
     */
    Result editCity(City city, String cityId);

    /**
     * 删除城市
     * @param cityId
     * @return
     */
    Result deleteCity(String cityId);

    /**
     * 根据ID查询城市
     * @param cityId
     * @return
     */
    Result getCityById(String cityId);

    /**
     * 根据参数查询城市列表
     * @param city
     * @return
     */
    Result getCityByParam(City city);

    /**
     * 根据参数查询城市列表并分页
     * @param city
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result getCityByParamWithPage(City city, Integer pageNo, Integer pageSize);
}
