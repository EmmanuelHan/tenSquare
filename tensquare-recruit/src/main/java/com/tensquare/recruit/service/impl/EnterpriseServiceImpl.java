package com.tensquare.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StringUtil;
import com.tensquare.recruit.entity.Enterprise;
import com.tensquare.recruit.mapper.EnterpriseMapper;
import com.tensquare.recruit.service.IEnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        IPage<Enterprise> enterprisePage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();

        if(enterprise.getId()!=null && !"".equals(enterprise.getId())){
            wrapper.eq("id",enterprise.getId());
        }
        if(enterprise.getName()!=null && !"".equals(enterprise.getName())){
            wrapper.eq("name",enterprise.getName());
        }
        if(enterprise.getSummary()!=null && !"".equals(enterprise.getSummary())){
            wrapper.eq("summary",enterprise.getSummary());
        }
        if(enterprise.getAddress()!=null && !"".equals(enterprise.getAddress())){
            wrapper.eq("address",enterprise.getAddress());
        }
        if(enterprise.getLabels()!=null && !"".equals(enterprise.getLabels())){
            wrapper.eq("labels",enterprise.getLabels());
        }
        if(enterprise.getCoordinate()!=null && !"".equals(enterprise.getCoordinate())){
            wrapper.eq("coordinate",enterprise.getCoordinate());
        }
        if(enterprise.getIshot()!=null && !"".equals(enterprise.getIshot())){
            wrapper.eq("ishot",enterprise.getIshot());
        }
        if(enterprise.getLogo()!=null && !"".equals(enterprise.getLogo())){
            wrapper.eq("logo",enterprise.getLogo());
        }
        if(enterprise.getJobCount()!=null && !"".equals(enterprise.getJobCount())){
            wrapper.eq("job_count",enterprise.getJobCount());
        }
        if(enterprise.getUrl()!=null && !"".equals(enterprise.getUrl())){
            wrapper.eq("url",enterprise.getUrl());
        }
        IPage<Enterprise> enterpriseIPage = page(enterprisePage, wrapper);

        return new Result(enterpriseIPage);
    }

}
