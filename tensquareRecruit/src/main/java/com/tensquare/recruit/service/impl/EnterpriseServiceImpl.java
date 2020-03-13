package com.tensquare.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.recruit.entity.Enterprise;
import com.tensquare.recruit.mapper.EnterpriseMapper;
import com.tensquare.recruit.service.IEnterpriseService;
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
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {

    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public List<Enterprise> list() {

       return enterpriseMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param enterprise  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Enterprise enterprise,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page enterprisePage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(enterprise.getId()!=null && !"".equals(enterprise.getId())){
            ((QueryWrapper) wrapper).eq("id",enterprise.getId());
        }
        if(enterprise.getName()!=null && !"".equals(enterprise.getName())){
            ((QueryWrapper) wrapper).eq("name",enterprise.getName());
        }
        if(enterprise.getSummary()!=null && !"".equals(enterprise.getSummary())){
            ((QueryWrapper) wrapper).eq("summary",enterprise.getSummary());
        }
        if(enterprise.getAddress()!=null && !"".equals(enterprise.getAddress())){
            ((QueryWrapper) wrapper).eq("address",enterprise.getAddress());
        }
        if(enterprise.getLabels()!=null && !"".equals(enterprise.getLabels())){
            ((QueryWrapper) wrapper).eq("labels",enterprise.getLabels());
        }
        if(enterprise.getCoordinate()!=null && !"".equals(enterprise.getCoordinate())){
            ((QueryWrapper) wrapper).eq("coordinate",enterprise.getCoordinate());
        }
        if(enterprise.getIshot()!=null && !"".equals(enterprise.getIshot())){
            ((QueryWrapper) wrapper).eq("ishot",enterprise.getIshot());
        }
        if(enterprise.getLogo()!=null && !"".equals(enterprise.getLogo())){
            ((QueryWrapper) wrapper).eq("logo",enterprise.getLogo());
        }
        if(enterprise.getJobCount()!=null && !"".equals(enterprise.getJobCount())){
            ((QueryWrapper) wrapper).eq("job_count",enterprise.getJobCount());
        }
        if(enterprise.getUrl()!=null && !"".equals(enterprise.getUrl())){
            ((QueryWrapper) wrapper).eq("url",enterprise.getUrl());
        }
        IPage<Enterprise> enterpriseIPage = enterpriseMapper.selectPage(enterprisePage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", enterprisePage.getTotal());
        data.put("pageNo", enterprisePage.getCurrent());
        data.put("list", enterpriseIPage.getRecords());
        return new Result(data);
    }

}