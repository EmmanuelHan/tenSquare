package com.tensquare.gathering.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.mapper.GatheringMapper;
import com.tensquare.gathering.service.IGatheringService;
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
        Page gatheringPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(gathering.getId()!=null && !"".equals(gathering.getId())){
            ((QueryWrapper) wrapper).eq("id",gathering.getId());
        }
        if(gathering.getName()!=null && !"".equals(gathering.getName())){
            ((QueryWrapper) wrapper).eq("name",gathering.getName());
        }
        if(gathering.getSummary()!=null && !"".equals(gathering.getSummary())){
            ((QueryWrapper) wrapper).eq("summary",gathering.getSummary());
        }
        if(gathering.getDetail()!=null && !"".equals(gathering.getDetail())){
            ((QueryWrapper) wrapper).eq("detail",gathering.getDetail());
        }
        if(gathering.getSponsor()!=null && !"".equals(gathering.getSponsor())){
            ((QueryWrapper) wrapper).eq("sponsor",gathering.getSponsor());
        }
        if(gathering.getImage()!=null && !"".equals(gathering.getImage())){
            ((QueryWrapper) wrapper).eq("image",gathering.getImage());
        }
        if(gathering.getStartTime()!=null && !"".equals(gathering.getStartTime())){
            ((QueryWrapper) wrapper).eq("start_time",gathering.getStartTime());
        }
        if(gathering.getEndTime()!=null && !"".equals(gathering.getEndTime())){
            ((QueryWrapper) wrapper).eq("end_time",gathering.getEndTime());
        }
        if(gathering.getAddress()!=null && !"".equals(gathering.getAddress())){
            ((QueryWrapper) wrapper).eq("address",gathering.getAddress());
        }
        if(gathering.getEnrollTime()!=null && !"".equals(gathering.getEnrollTime())){
            ((QueryWrapper) wrapper).eq("enroll_time",gathering.getEnrollTime());
        }
        if(gathering.getState()!=null && !"".equals(gathering.getState())){
            ((QueryWrapper) wrapper).eq("state",gathering.getState());
        }
        if(gathering.getCity()!=null && !"".equals(gathering.getCity())){
            ((QueryWrapper) wrapper).eq("city",gathering.getCity());
        }
        IPage<Gathering> gatheringIPage = gatheringMapper.selectPage(gatheringPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", gatheringPage.getTotal());
        data.put("pageNo", gatheringPage.getCurrent());
        data.put("list", gatheringIPage.getRecords());
        return new Result(data);
    }

}
