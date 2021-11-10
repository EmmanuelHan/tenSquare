package com.tensquare.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StringUtil;
import com.tensquare.qa.entity.Pl;
import com.tensquare.qa.mapper.PlMapper;
import com.tensquare.qa.service.IPlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@Service
public class PlServiceImpl extends ServiceImpl<PlMapper, Pl> implements IPlService {


    /**
     * 分页查询
     *
     * @param pl page  limit
     * @return jsonResponse
     */
    @Override
    public Result findByParam(Pl pl, Integer page, Integer limit) {

        if (page == null) {
            page = StringUtil.START_PAGE;
        }
        if (limit == null) {
            limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        log.info("pageSize: {},pageNo: {}", limit, page);
        IPage<Pl> plPage = new Page<>(page, limit);
        //查询构造器
        QueryWrapper<Pl> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(pl.getProblemId()), "problem_id", pl.getProblemId());
        wrapper.eq(!ObjectUtils.isEmpty(pl.getLabelId()), "label_id", pl.getLabelId());
        IPage<Pl> plIPage = page(plPage, wrapper);
        return new Result(plIPage);
    }

}
