package com.tensquare.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.qa.entity.Pl;
import com.tensquare.qa.mapper.PlMapper;
import com.tensquare.qa.service.IPlService;
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
public class PlServiceImpl extends ServiceImpl<PlMapper, Pl> implements IPlService {

    @Resource
    private PlMapper plMapper;

    @Override
    public List<Pl> list() {

       return plMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param pl  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Pl pl,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<Pl> plPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Pl> wrapper = new QueryWrapper<>();

        if(pl.getProblemId()!=null && !"".equals(pl.getProblemId())){
            wrapper.eq("problem_id",pl.getProblemId());
        }
        if(pl.getLabelId()!=null && !"".equals(pl.getLabelId())){
            wrapper.eq("label_id",pl.getLabelId());
        }
        IPage<Pl> plIPage = page(plPage, wrapper);

        return new Result(plIPage);
    }

}
