package com.tensquare.gathering.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.gathering.entity.UserGath;
import com.tensquare.gathering.mapper.UserGathMapper;
import com.tensquare.gathering.service.IUserGathService;
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
public class UserGathServiceImpl extends ServiceImpl<UserGathMapper, UserGath> implements IUserGathService {

    @Resource
    private UserGathMapper userGathMapper;

    @Override
    public List<UserGath> list() {

       return userGathMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param userGath  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(UserGath userGath,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<UserGath> userGathPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<UserGath> wrapper = new QueryWrapper<>();

        if(userGath.getUserId()!=null && !"".equals(userGath.getUserId())){
            wrapper.eq("user_id",userGath.getUserId());
        }
        if(userGath.getGathId()!=null && !"".equals(userGath.getGathId())){
            wrapper.eq("gath_id",userGath.getGathId());
        }
        if(userGath.getExeTime()!=null && !"".equals(userGath.getExeTime())){
            wrapper.eq("exe_time",userGath.getExeTime());
        }
        IPage<UserGath> userGathIPage = page(userGathPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", userGathPage.getTotal());
        data.put("pageNo", userGathPage.getCurrent());
        data.put("list", userGathIPage.getRecords());
        return new Result(data);
    }

}
