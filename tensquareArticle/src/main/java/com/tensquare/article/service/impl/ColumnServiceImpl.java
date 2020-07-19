package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.article.entity.Column;
import com.tensquare.article.mapper.ColumnMapper;
import com.tensquare.article.service.IColumnService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.StringUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date 2020-03-12
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
     * @param column page  limit
     * @return jsonResponse
     */
    @Override
    public Result findByParam(Column column, Integer page, Integer limit) {

        //开启分页
        IPage<Column> columnPage = new Page<>(page, limit);
        //查询构造器
        QueryWrapper<Column> wrapper = new QueryWrapper<>();

        if (column.getId() != null && !"".equals(column.getId())) {
            wrapper.eq("id", column.getId());
        }
        if (column.getName() != null && !"".equals(column.getName())) {
            wrapper.eq("name", column.getName());
        }
        if (column.getSummary() != null && !"".equals(column.getSummary())) {
            wrapper.eq("summary", column.getSummary());
        }
        if (column.getUserId() != null && !"".equals(column.getUserId())) {
            wrapper.eq("user_id", column.getUserId());
        }
        if (column.getCreateTime() != null && !"".equals(column.getCreateTime())) {
            wrapper.eq("create_time", column.getCreateTime());
        }
        if (column.getCheckTime() != null && !"".equals(column.getCheckTime())) {
            wrapper.eq("check_time", column.getCheckTime());
        }
        if (column.getState() != null && !"".equals(column.getState())) {
            wrapper.eq("state", column.getState());
        }
        IPage<Column> columnIPage = page(columnPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", columnIPage.getSize());
        data.put("total", columnIPage.getTotal());
        data.put("pageNo", columnIPage.getCurrent());
        data.put("list", columnIPage.getRecords());
        return new Result(data);
    }

    /**
     * 增加专栏
     *
     * @param column
     * @return
     */
    @Override
    public Result columnAdd(Column column) {
        column.setCreateTime(new Date());
        column.setState("2");//待审核
        save(column);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 专栏全部列表
     *
     * @return
     */
    @Override
    public Result columnAllList() {
        QueryWrapper<Column> wrapper = new QueryWrapper<>();
        wrapper.eq("state", "1");//审核通过
        List<Column> list = list(wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        return new Result(data);
    }

    /**
     * 根据ID查询专栏
     *
     * @param columnId
     * @return
     */
    @Override
    public Result columnById(String columnId) {
        Column column = getById(columnId);

        Map<String, Object> data = new HashMap<>();
        data.put("column", column);
        return new Result(data);
    }

    /**
     * 根据Id修改专栏
     *
     * @param columnId
     * @param column
     * @return
     */
    @Override
    public Result columnEdit(String columnId, Column column) {
        column.setId(columnId);
        updateById(column);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID删除专栏
     *
     * @param columnId
     * @return
     */
    @Override
    public Result columnDeleteById(String columnId) {
        UpdateWrapper<Column> wrapper = new UpdateWrapper<>();
        wrapper.set("state", "0");
        wrapper.eq("id", columnId);
        update(wrapper);

        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据条件查询专栏列表
     *
     * @param column
     * @return
     */
    @Override
    public Result columnByParam(Column column) {
        QueryWrapper<Column> wrapper = new QueryWrapper<>();

        if (!ObjectUtils.isEmpty(column.getName())) {
            wrapper.like("name", column.getName());
        }
        if (!ObjectUtils.isEmpty(column.getSummary())) {
            wrapper.like("summary", column.getSummary());
        }
        if (!ObjectUtils.isEmpty(column.getUserId())) {
            wrapper.eq("user_id", column.getUserId());
        }
        if (!ObjectUtils.isEmpty(column.getCreateTime())) {
            wrapper.eq("create_time", column.getCreateTime());
        }
        if (!ObjectUtils.isEmpty(column.getCheckTime())) {
            wrapper.eq("check_time", column.getCheckTime());
        }
        wrapper.eq("state", "1");
        List<Column> list = list(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("list",list);
        return new Result(data);
    }

    /**
     * 根据条件查询专栏列表并分页
     *
     * @param pageNo
     * @param pageSize
     * @param column
     * @return
     */
    @Override
    public Result columnByParamWithPage(int pageNo, int pageSize, Column column) {
        //开启分页
        IPage<Column> columnPage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Column> wrapper = new QueryWrapper<>();

        if (!ObjectUtils.isEmpty(column.getName())) {
            wrapper.like("name", column.getName());
        }
        if (!ObjectUtils.isEmpty(column.getSummary())) {
            wrapper.like("summary", column.getSummary());
        }
        if (!ObjectUtils.isEmpty(column.getUserId())) {
            wrapper.eq("user_id", column.getUserId());
        }
        if (!ObjectUtils.isEmpty(column.getCreateTime())) {
            wrapper.eq("create_time", column.getCreateTime());
        }
        if (!ObjectUtils.isEmpty(column.getCheckTime())) {
            wrapper.eq("check_time", column.getCheckTime());
        }
        IPage<Column> columnIPage = page(columnPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", columnIPage.getSize());
        data.put("total", columnIPage.getTotal());
        data.put("pageNo", columnIPage.getCurrent());
        data.put("list", columnIPage.getRecords());
        return new Result(data);
    }

    /**
     * 申请专栏
     *
     * @param column
     * @return
     */
    @Override
    public Result columnApply(Column column) {



        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 审核专栏
     *
     * @param columnId
     * @return
     */
    @Override
    public Result columnExamine(String columnId) {
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据用户ID查询专栏列表
     *
     * @param userId
     * @return
     */
    @Override
    public Result columnByUserId(String userId) {
        QueryWrapper<Column> wrapper = new QueryWrapper<>();
        wrapper.eq("state","1");
        wrapper.eq("user_id",userId);
        List<Column> list = list(wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("list",list);
        return new Result(data);
    }

}
