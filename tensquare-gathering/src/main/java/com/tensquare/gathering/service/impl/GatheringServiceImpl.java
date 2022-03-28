package com.tensquare.gathering.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StringUtil;
import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.mapper.GatheringMapper;
import com.tensquare.gathering.service.IGatheringService;
import lombok.extern.slf4j.Slf4j;
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
public class GatheringServiceImpl extends ServiceImpl<GatheringMapper, Gathering> implements IGatheringService {

    @Resource
    private GatheringMapper gatheringMapper;

    @Override
    public List<Gathering> list() {

       return gatheringMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param gathering  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Gathering gathering,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<Gathering> gatheringPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Gathering> wrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(gathering.getId())){
            wrapper.eq("id",gathering.getId());
        }
        if(!ObjectUtils.isEmpty(gathering.getName())){
            wrapper.eq("name",gathering.getName());
        }
        if(!ObjectUtils.isEmpty(gathering.getSummary())){
            wrapper.eq("summary",gathering.getSummary());
        }
        if(!ObjectUtils.isEmpty(gathering.getDetail())){
            wrapper.eq("detail",gathering.getDetail());
        }
        if(!ObjectUtils.isEmpty(gathering.getSponsor())){
            wrapper.eq("sponsor",gathering.getSponsor());
        }
        if(!ObjectUtils.isEmpty(gathering.getImage())){
            wrapper.eq("image",gathering.getImage());
        }
        if(!ObjectUtils.isEmpty(gathering.getStartTime())){
            wrapper.eq("start_time",gathering.getStartTime());
        }
        if(!ObjectUtils.isEmpty(gathering.getEndTime())){
            wrapper.eq("end_time",gathering.getEndTime());
        }
        if(!ObjectUtils.isEmpty(gathering.getAddress())){
            wrapper.eq("address",gathering.getAddress());
        }
        if(!ObjectUtils.isEmpty(gathering.getEnrollTime())){
            wrapper.eq("enroll_time",gathering.getEnrollTime());
        }
        if(!ObjectUtils.isEmpty(gathering.getState())){
            wrapper.eq("state",gathering.getState());
        }
        if(!ObjectUtils.isEmpty(gathering.getCity())){
            wrapper.eq("city",gathering.getCity());
        }
        IPage<Gathering> gatheringIPage = page(gatheringPage, wrapper);

        return new Result(gatheringIPage);
    }

}
