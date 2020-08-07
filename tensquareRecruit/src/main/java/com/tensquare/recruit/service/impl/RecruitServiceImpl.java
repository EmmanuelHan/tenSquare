package com.tensquare.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.recruit.entity.Recruit;
import com.tensquare.recruit.mapper.RecruitMapper;
import com.tensquare.recruit.service.IRecruitService;
import entity.Result;
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
public class RecruitServiceImpl extends ServiceImpl<RecruitMapper, Recruit> implements IRecruitService {

    @Resource
    private RecruitMapper recruitMapper;

    @Override
    public List<Recruit> list() {

       return recruitMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param recruit  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Recruit recruit,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<Recruit> recruitPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(recruit.getId())){
            wrapper.eq("id",recruit.getId());
        }
        if(!ObjectUtils.isEmpty(recruit.getJobName())){
            wrapper.eq("job_name",recruit.getJobName());
        }
        if(!ObjectUtils.isEmpty(recruit.getSalary())){
            wrapper.eq("salary",recruit.getSalary());
        }
        if(!ObjectUtils.isEmpty(recruit.getCondition())){
            wrapper.eq("condition",recruit.getCondition());
        }
        if(!ObjectUtils.isEmpty(recruit.getEducation())){
            wrapper.eq("education",recruit.getEducation());
        }
        if(!ObjectUtils.isEmpty(recruit.getType())){
            wrapper.eq("type",recruit.getType());
        }
        if(!ObjectUtils.isEmpty(recruit.getAddress())){
            wrapper.eq("address",recruit.getAddress());
        }
        if(!ObjectUtils.isEmpty(recruit.getEId())){
            wrapper.eq("e_id",recruit.getEId());
        }
        if(!ObjectUtils.isEmpty(recruit.getCreateTime())){
            wrapper.eq("create_time",recruit.getCreateTime());
        }
        if(!ObjectUtils.isEmpty(recruit.getState())){
            wrapper.eq("state",recruit.getState());
        }
        if(!ObjectUtils.isEmpty(recruit.getUrl())){
            wrapper.eq("url",recruit.getUrl());
        }
        if(!ObjectUtils.isEmpty(recruit.getLabel())){
            wrapper.eq("label",recruit.getLabel());
        }
        if(!ObjectUtils.isEmpty(recruit.getContentDescr())){
            wrapper.eq("content_descr",recruit.getContentDescr());
        }
        if(!ObjectUtils.isEmpty(recruit.getContentRequire())){
            wrapper.eq("content_require",recruit.getContentRequire());
        }
        IPage<Recruit> recruitIPage = page(recruitPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", recruitPage.getTotal());
        data.put("pageNo", recruitPage.getCurrent());
        data.put("list", recruitIPage.getRecords());
        return new Result(data);
    }

}
