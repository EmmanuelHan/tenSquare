package com.tensquare.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.base.entity.City;
import com.tensquare.base.mapper.CityMapper;
import com.tensquare.base.service.ICityService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Resource
    private CityMapper cityMapper;

    /**
     * 新增城市
     * @param city
     * @return
     */
    @Override
    public Result addCity(City city){
        save(city);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 返回城市列表
     * @return
     */
    @Override
    public Result getCityList(){
        List<City> list = list();
        return new Result(list);
    }

    /**
     * 修改城市
     * @param city
     * @param cityId
     * @return
     */
    @Override
    public Result editCity(City city, String cityId){
        city.setId(cityId);
        updateById(city);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 删除城市
     * @param cityId
     * @return
     */
    @Override
    public Result deleteCity(String cityId){
        removeById(cityId);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID查询城市
     * @param cityId
     * @return
     */
    @Override
    public Result getCityById(String cityId){
        City byId = getById(cityId);
        return new Result(byId);
    }

    /**
     * 根据参数查询城市列表
     * @param city
     * @return
     */
    @Override
    public Result getCityByParam(City city){
        Wrapper wrapper = new QueryWrapper();
        if(!ObjectUtils.isEmpty(city.getId())){
            ((QueryWrapper) wrapper).eq("id",city.getId());
        }
        if(!ObjectUtils.isEmpty(city.getName())){
            ((QueryWrapper) wrapper).like("name",city.getName());
        }
        if(!ObjectUtils.isEmpty(city.getIshot())){
            ((QueryWrapper) wrapper).eq("ishot",city.getIshot());
        }
        List<City> list = list(wrapper);
        return new Result(list);
    }

    /**
     * 根据参数查询城市列表并分页
     * @param city
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result getCityByParamWithPage(City city, Integer pageNo, Integer pageSize){
        if(pageNo == null){
            pageNo = StringUtil.START_PAGE;
        }
        if(pageSize == null){
            pageSize = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page cityPage = new Page(pageNo,pageSize);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(city.getId()!=null && !"".equals(city.getId())){
            ((QueryWrapper) wrapper).eq("id",city.getId());
        }
        if(city.getName()!=null && !"".equals(city.getName())){
            ((QueryWrapper) wrapper).eq("name",city.getName());
        }
        if(city.getIshot()!=null && !"".equals(city.getIshot())){
            ((QueryWrapper) wrapper).eq("ishot",city.getIshot());
        }
        IPage<City> cityIPage = cityMapper.selectPage(cityPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", pageSize);
        data.put("total", cityPage.getTotal());
        data.put("pageNo", cityPage.getCurrent());
        data.put("list", cityIPage.getRecords());
        return new Result(data);

    }


}
