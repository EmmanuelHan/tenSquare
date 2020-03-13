package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.article.entity.Column;
import com.tensquare.article.mapper.ColumnMapper;
import com.tensquare.article.service.IColumnService;
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
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {

    @Resource
    private ColumnMapper columnMapper;

    @Override
    public List<Column> list() {

       return columnMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param column  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Column column,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page columnPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(column.getId()!=null && !"".equals(column.getId())){
            ((QueryWrapper) wrapper).eq("id",column.getId());
        }
        if(column.getName()!=null && !"".equals(column.getName())){
            ((QueryWrapper) wrapper).eq("name",column.getName());
        }
        if(column.getSummary()!=null && !"".equals(column.getSummary())){
            ((QueryWrapper) wrapper).eq("summary",column.getSummary());
        }
        if(column.getUserId()!=null && !"".equals(column.getUserId())){
            ((QueryWrapper) wrapper).eq("user_id",column.getUserId());
        }
        if(column.getCreateTime()!=null && !"".equals(column.getCreateTime())){
            ((QueryWrapper) wrapper).eq("create_time",column.getCreateTime());
        }
        if(column.getCheckTime()!=null && !"".equals(column.getCheckTime())){
            ((QueryWrapper) wrapper).eq("check_time",column.getCheckTime());
        }
        if(column.getState()!=null && !"".equals(column.getState())){
            ((QueryWrapper) wrapper).eq("state",column.getState());
        }
        IPage<Column> columnIPage = columnMapper.selectPage(columnPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", columnPage.getTotal());
        data.put("pageNo", columnPage.getCurrent());
        data.put("list", columnIPage.getRecords());
        return new Result(data);
    }

}
