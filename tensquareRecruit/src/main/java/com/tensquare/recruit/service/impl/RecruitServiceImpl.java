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
        Page recruitPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(recruit.getId()!=null && !"".equals(recruit.getId())){
            ((QueryWrapper) wrapper).eq("id",recruit.getId());
        }
        if(recruit.getJobName()!=null && !"".equals(recruit.getJobName())){
            ((QueryWrapper) wrapper).eq("job_name",recruit.getJobName());
        }
        if(recruit.getSalary()!=null && !"".equals(recruit.getSalary())){
            ((QueryWrapper) wrapper).eq("salary",recruit.getSalary());
        }
        if(recruit.getCondition()!=null && !"".equals(recruit.getCondition())){
            ((QueryWrapper) wrapper).eq("condition",recruit.getCondition());
        }
        if(recruit.getEducation()!=null && !"".equals(recruit.getEducation())){
            ((QueryWrapper) wrapper).eq("education",recruit.getEducation());
        }
        if(recruit.getType()!=null && !"".equals(recruit.getType())){
            ((QueryWrapper) wrapper).eq("type",recruit.getType());
        }
        if(recruit.getAddress()!=null && !"".equals(recruit.getAddress())){
            ((QueryWrapper) wrapper).eq("address",recruit.getAddress());
        }
        if(recruit.getEId()!=null && !"".equals(recruit.getEId())){
            ((QueryWrapper) wrapper).eq("e_id",recruit.getEId());
        }
        if(recruit.getCreateTime()!=null && !"".equals(recruit.getCreateTime())){
            ((QueryWrapper) wrapper).eq("create_time",recruit.getCreateTime());
        }
        if(recruit.getState()!=null && !"".equals(recruit.getState())){
            ((QueryWrapper) wrapper).eq("state",recruit.getState());
        }
        if(recruit.getUrl()!=null && !"".equals(recruit.getUrl())){
            ((QueryWrapper) wrapper).eq("url",recruit.getUrl());
        }
        if(recruit.getLabel()!=null && !"".equals(recruit.getLabel())){
            ((QueryWrapper) wrapper).eq("label",recruit.getLabel());
        }
        if(recruit.getContentDescr()!=null && !"".equals(recruit.getContentDescr())){
            ((QueryWrapper) wrapper).eq("content_descr",recruit.getContentDescr());
        }
        if(recruit.getContentRequire()!=null && !"".equals(recruit.getContentRequire())){
            ((QueryWrapper) wrapper).eq("content_require",recruit.getContentRequire());
        }
        IPage<Recruit> recruitIPage = recruitMapper.selectPage(recruitPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", recruitPage.getTotal());
        data.put("pageNo", recruitPage.getCurrent());
        data.put("list", recruitIPage.getRecords());
        return new Result(data);
    }

}
