package com.tensquare.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.base.entity.Label;
import com.tensquare.base.mapper.LabelMapper;
import com.tensquare.base.service.ILabelService;
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
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Resource
    private LabelMapper labelMapper;

    @Override
    public List<Label> list() {

       return labelMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param label  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Label label,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page labelPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(label.getId()!=null && !"".equals(label.getId())){
            ((QueryWrapper) wrapper).eq("id",label.getId());
        }
        if(label.getLabelName()!=null && !"".equals(label.getLabelName())){
            ((QueryWrapper) wrapper).eq("label_name",label.getLabelName());
        }
        if(label.getState()!=null && !"".equals(label.getState())){
            ((QueryWrapper) wrapper).eq("state",label.getState());
        }
        if(label.getCount()!=null && !"".equals(label.getCount())){
            ((QueryWrapper) wrapper).eq("count",label.getCount());
        }
        if(label.getRecommend()!=null && !"".equals(label.getRecommend())){
            ((QueryWrapper) wrapper).eq("recommend",label.getRecommend());
        }
        if(label.getFans()!=null && !"".equals(label.getFans())){
            ((QueryWrapper) wrapper).eq("fans",label.getFans());
        }
        IPage<Label> labelIPage = labelMapper.selectPage(labelPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", labelPage.getTotal());
        data.put("pageNo", labelPage.getCurrent());
        data.put("list", labelIPage.getRecords());
        return new Result(data);
    }

}
