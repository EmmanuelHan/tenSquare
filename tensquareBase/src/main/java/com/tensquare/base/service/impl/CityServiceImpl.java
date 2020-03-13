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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    @Override
    public List<City> list() {

       return cityMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param city  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(City city,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page cityPage = new Page(page,limit);
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
        data.put("pageSize", page);
        data.put("total", cityPage.getTotal());
        data.put("pageNo", cityPage.getCurrent());
        data.put("list", cityIPage.getRecords());
        return new Result(data);
    }

}
