package com.tensquare.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.mapper.ProblemMapper;
import com.tensquare.qa.service.IProblemService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import util.StringUtil;

import javax.annotation.Resource;
import java.util.Date;
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

        //开启分页
        IPage<Problem> problemPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();

        if(problem.getId()!=null && !"".equals(problem.getId())){
            wrapper.eq("id",problem.getId());
        }
        if(problem.getTitle()!=null && !"".equals(problem.getTitle())){
            wrapper.eq("title",problem.getTitle());
        }
        if(problem.getContent()!=null && !"".equals(problem.getContent())){
            wrapper.eq("content",problem.getContent());
        }
        if(problem.getCreateTime()!=null && !"".equals(problem.getCreateTime())){
            wrapper.eq("create_time",problem.getCreateTime());
        }
        if(problem.getUpdateTime()!=null && !"".equals(problem.getUpdateTime())){
            wrapper.eq("update_time",problem.getUpdateTime());
        }
        if(problem.getUserId()!=null && !"".equals(problem.getUserId())){
            wrapper.eq("user_id",problem.getUserId());
        }
        if(problem.getNickName()!=null && !"".equals(problem.getNickName())){
            wrapper.eq("nick_name",problem.getNickName());
        }
        if(problem.getVisits()!=null && !"".equals(problem.getVisits())){
            wrapper.eq("visits",problem.getVisits());
        }
        if(problem.getThumbUp()!=null && !"".equals(problem.getThumbUp())){
            wrapper.eq("thumb_up",problem.getThumbUp());
        }
        if(problem.getReply()!=null && !"".equals(problem.getReply())){
            wrapper.eq("reply",problem.getReply());
        }
        if(problem.getSolve()!=null && !"".equals(problem.getSolve())){
            wrapper.eq("solve",problem.getSolve());
        }
        if(problem.getReplyName()!=null && !"".equals(problem.getReplyName())){
            wrapper.eq("reply_name",problem.getReplyName());
        }
        if(problem.getReplyTime()!=null && !"".equals(problem.getReplyTime())){
            wrapper.eq("reply_time",problem.getReplyTime());
        }
        IPage<Problem> problemIPage = page(problemPage, wrapper);

        return new Result(problemIPage);
    }

    /**
     * 增加问题
     */
    @Override
    public Result addCity(Problem problem) throws Exception{

        Date now = new Date();
        problem.setUpdateTime(now);
        problem.setCreateTime(now);
        problem.setVisits(0);
        problem.setThumbUp(0);
        problem.setReply(0);
        problem.setSolve("0");

        save(problem);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * Problem全部列表
     */
    @Override
    public Result cityList()throws Exception{
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        List<Problem> list = list(wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("list",list);
        return new Result(data);
    }

    /**
     * 根据ID查询问题
     */
    @Override
    public Result selectById(String problemId) throws Exception{
        Problem problem = getById(problemId);
        Map<String,Object> data = new HashMap<>();
        data.put("problem",problem);
        return new Result(data);
    }

    /**
     * 修改问题
     */
    @Override
    public Result editProvlem(Problem problem, String problemId) throws Exception{
        problem.setId(problemId);
        problem.setUpdateTime(new Date());
        updateById(problem);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID删除问题
     */
    @Override
    public Result deleteById(String problemId) throws Exception{
        removeById(problemId);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据条件查询问题列表
     */
    @Override
    public Result selectByParam(Problem problem) throws Exception{
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(problem.getTitle())){
            wrapper.eq("title",problem.getTitle());
        }
        if(!ObjectUtils.isEmpty(problem.getContent())){
            wrapper.eq("content",problem.getContent());
        }
        List<Problem> list = list(wrapper);
        Map<String,Object> data = new HashMap<>();
        data.put("list",list);
        return new Result(data);
    }

    /**
     * 问题分页
     */
    @Override
    public Result selectByParamWithPage(Problem problem, int pageNo, int pageSize) throws Exception{
        IPage<Problem> page = new Page<>(pageNo,pageSize);

        QueryWrapper<Problem> wrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(problem.getTitle())){
            wrapper.eq("title",problem.getTitle());
        }
        if(!ObjectUtils.isEmpty(problem.getContent())){
            wrapper.eq("content",problem.getContent());
        }
        IPage<Problem> problemIPage = page(page, wrapper);

        return new Result(problemIPage);
    }

    /**
     * 最新问答列表
     */
    @Override
    public Result selectNewestListWithPage(String labelId, int pageNo, int pageSize) throws Exception{




        Map<String,Object> data = new HashMap<>();
        return new Result(data);
    }

    /**
     * 热门问答列表
     */
    @Override
    public Result selectHotListWithPage(String labelId, int pageNo, int pageSize) throws Exception{
        Map<String,Object> data = new HashMap<>();
        return new Result(data);
    }

    /**
     * 等待回答列表
     */
    @Override
    public Result selectWaitListWithPage(String labelId, int pageNo, int pageSize) throws Exception{
        Map<String,Object> data = new HashMap<>();
        return new Result(data);
    }

    /**
     * Problem分页
     */
    @Override
    public Result selectListParamWithPage(String labelId, int pageNo, int pageSize) throws Exception{
        Map<String,Object> data = new HashMap<>();
        return new Result(data);
    }
}
