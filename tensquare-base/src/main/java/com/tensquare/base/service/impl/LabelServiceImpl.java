package com.tensquare.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.base.entity.Label;
import com.tensquare.base.mapper.LabelMapper;
import com.tensquare.base.service.ILabelService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.StringUtil;
import util.Type;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Resource
    private LabelMapper labelMapper;

    /**
     * 增加标签
     *
     * @param label
     * @return
     */
    @Override
    public Result addLabel(Label label) {
        save(label);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 标签全部列表
     *
     * @return
     */
    @Override
    public Result getLabelList() {
        List<Label> list = list();
        return new Result(list);
    }

    /**
     * 推荐标签列表
     *
     * @return
     */
    @Override
    public Result getLabelTopList() {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.eq("recommend", Type.RECOMMEND);
        wrapper.eq("state", Type.STATE_NORMAL);
        List<Label> list = list(wrapper);
        return new Result(list);
    }

    /**
     * 获取有效标签列表
     *
     * @return
     */
    @Override
    public Result getNormalLabelList() {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.eq("state", Type.STATE_NORMAL);
        List<Label> list = list(wrapper);
        return new Result(list);
    }

    /**
     * 根据Id查询
     *
     * @param labelId
     * @return
     */
    @Override
    public Result getLabelById(String labelId) {
        Label byId = getById(labelId);
        return new Result(byId);
    }

    /**
     * 修改标签
     *
     * @param labelId
     * @param label
     * @return
     */
    @Override
    public Result editLabel(String labelId, Label label) {
        label.setId(labelId);
        updateById(label);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID删除标签
     *
     * @param labelId
     * @return
     */
    @Override
    public Result deleteLabel(String labelId) {
        removeById(labelId);
        return new Result();
    }

    /**
     * 获取列表并分页
     *
     * @param label
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Result getLabelListWithPage(Label label, Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = StringUtil.START_PAGE;
        }
        if (pageSize == null) {
            pageSize = StringUtil.PAGE_SIZE;
        }
        //开启分页
        IPage<Label> labelPage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(label.getId())) {
            wrapper.eq("id", label.getId());
        }
        if (!ObjectUtils.isEmpty(label.getLabelName())) {
            wrapper.like("label_name", label.getLabelName());
        }
        if (!ObjectUtils.isEmpty(label.getState())) {
            wrapper.eq("state", label.getState());
        }
        if (!ObjectUtils.isEmpty(label.getCount())) {
            wrapper.eq("count", label.getCount());
        }
        if (!ObjectUtils.isEmpty(label.getRecommend())) {
            wrapper.eq("recommend", label.getRecommend());
        }
        if (!ObjectUtils.isEmpty(label.getFans())) {
            wrapper.eq("fans", label.getFans());
        }
        IPage<Label> labelIPage = page(labelPage, wrapper);

        return new Result(labelIPage);
    }

    /**
     * 根据条件查询列表
     *
     * @param label
     * @return
     */
    @Override
    public Result getLabelByParam(Label label) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(label.getId())) {
            wrapper.eq("id", label.getId());
        }
        if (!ObjectUtils.isEmpty(label.getLabelName())) {
            wrapper.like("label_name", label.getLabelName());
        }
        if (!ObjectUtils.isEmpty(label.getState())) {
            wrapper.eq("state", label.getState());
        }
        if (!ObjectUtils.isEmpty(label.getCount())) {
            wrapper.eq("count", label.getCount());
        }
        if (!ObjectUtils.isEmpty(label.getRecommend())) {
            wrapper.eq("recommend", label.getRecommend());
        }
        if (!ObjectUtils.isEmpty(label.getFans())) {
            wrapper.eq("fans", label.getFans());
        }
        List<Label> list = list(wrapper);
        return new Result(list);
    }


}
