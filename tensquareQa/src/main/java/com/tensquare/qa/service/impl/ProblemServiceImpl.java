package com.tensquare.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.mapper.ProblemMapper;
import com.tensquare.qa.service.IProblemService;
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
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Resource
    private ProblemMapper problemMapper;

    @Override
    public List<Problem> list() {

       return problemMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param problem  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Problem problem,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page problemPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(problem.getId()!=null && !"".equals(problem.getId())){
            ((QueryWrapper) wrapper).eq("id",problem.getId());
        }
        if(problem.getTitle()!=null && !"".equals(problem.getTitle())){
            ((QueryWrapper) wrapper).eq("title",problem.getTitle());
        }
        if(problem.getContent()!=null && !"".equals(problem.getContent())){
            ((QueryWrapper) wrapper).eq("content",problem.getContent());
        }
        if(problem.getCreateTime()!=null && !"".equals(problem.getCreateTime())){
            ((QueryWrapper) wrapper).eq("create_time",problem.getCreateTime());
        }
        if(problem.getUpdateTime()!=null && !"".equals(problem.getUpdateTime())){
            ((QueryWrapper) wrapper).eq("update_time",problem.getUpdateTime());
        }
        if(problem.getUserId()!=null && !"".equals(problem.getUserId())){
            ((QueryWrapper) wrapper).eq("user_id",problem.getUserId());
        }
        if(problem.getNickName()!=null && !"".equals(problem.getNickName())){
            ((QueryWrapper) wrapper).eq("nick_name",problem.getNickName());
        }
        if(problem.getVisits()!=null && !"".equals(problem.getVisits())){
            ((QueryWrapper) wrapper).eq("visits",problem.getVisits());
        }
        if(problem.getThumbUp()!=null && !"".equals(problem.getThumbUp())){
            ((QueryWrapper) wrapper).eq("thumb_up",problem.getThumbUp());
        }
        if(problem.getReply()!=null && !"".equals(problem.getReply())){
            ((QueryWrapper) wrapper).eq("reply",problem.getReply());
        }
        if(problem.getSolve()!=null && !"".equals(problem.getSolve())){
            ((QueryWrapper) wrapper).eq("solve",problem.getSolve());
        }
        if(problem.getReplyName()!=null && !"".equals(problem.getReplyName())){
            ((QueryWrapper) wrapper).eq("reply_name",problem.getReplyName());
        }
        if(problem.getReplyTime()!=null && !"".equals(problem.getReplyTime())){
            ((QueryWrapper) wrapper).eq("reply_time",problem.getReplyTime());
        }
        IPage<Problem> problemIPage = problemMapper.selectPage(problemPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", problemPage.getTotal());
        data.put("pageNo", problemPage.getCurrent());
        data.put("list", problemIPage.getRecords());
        return new Result(data);
    }

}
