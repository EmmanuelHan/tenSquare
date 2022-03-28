package com.tensquare.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.base.entity.City;
import com.tensquare.base.mapper.CityMapper;
import com.tensquare.base.service.ICityService;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import com.tensquare.common.util.StringUtil;
import com.tensquare.common.util.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Service
@RefreshScope //使用自定义的配置文件的值时，采用springConfig 重新加载自定义值
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
        //判断是否存在相同城市
        if(judgeSaveName(city.getId(),city.getName())) {
            return new Result(ResultEnum.SAVE_CITY);
        }
        if(ObjectUtils.isEmpty(city.getHot())){
            city.setHot(Type.NOT_HOT);
        }
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
        QueryWrapper<City> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(city.getId())){
            wrapper.eq("id",city.getId());
        }
        if(!ObjectUtils.isEmpty(city.getName())){
            wrapper.like("name",city.getName());
        }
        if(!ObjectUtils.isEmpty(city.getHot())){
            wrapper.eq("ishot",city.getHot());
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
        IPage<City> cityPage = new Page<>(pageNo,pageSize);
        //查询构造器
        QueryWrapper<City> wrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(city.getId())){
            wrapper.eq("id",city.getId());
        }
        if(!ObjectUtils.isEmpty(city.getName())){
            wrapper.eq("name",city.getName());
        }
        if(!ObjectUtils.isEmpty(city.getHot())){
            wrapper.eq("hot",city.getHot());
        }
        IPage<City> cityIPage = page(cityPage, wrapper);

        return new Result(cityIPage);

    }

    /**
     * 根据id和name判断是否有重复数据
     * @param id
     * @param name
     * @return
     */
    public boolean judgeSaveName(Object id,String name){
        QueryWrapper<City> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(name)){
            wrapper.eq("name", name);
        }
        if(!ObjectUtils.isEmpty(id)){
            wrapper.ne("id", id);
        }
        int count = count(wrapper);
        return count > 0;
    }


}
