package com.tensquare.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     * @param problem page  limit
     * @return jsonResponse
     */
    @Override
    public Result findByParam(Problem problem, Integer page, Integer limit) {

        //开启分页
        IPage<Problem> problemPage = new Page<>(page, limit);
        //查询构造器
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(problem.getId()), "id", problem.getId());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getTitle()), "title", problem.getTitle());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getContent()), "content", problem.getContent());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getCreateTime()), "create_time", problem.getCreateTime());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getUpdateTime()), "update_time", problem.getUpdateTime());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getUserId()), "user_id", problem.getUserId());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getNickName()), "nick_name", problem.getNickName());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getVisits()), "visits", problem.getVisits());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getThumbUp()), "thumb_up", problem.getThumbUp());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getReply()), "reply", problem.getReply());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getSolve()), "solve", problem.getSolve());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getReplyName()), "reply_name", problem.getReplyName());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getReplyTime()), "reply_time", problem.getReplyTime());
        IPage<Problem> problemIPage = page(problemPage, wrapper);
        return new Result(problemIPage);
    }

    /**
     * 增加问题
     */
    @Override
    public Result addProblem(Problem problem) throws Exception {
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
    public Result cityList() throws Exception {
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        List<Problem> list = list(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        return new Result(data);
    }

    /**
     * 根据ID查询问题
     */
    @Override
    public Result selectById(String problemId) throws Exception {
        Problem problem = getById(problemId);
        Map<String, Object> data = new HashMap<>();
        data.put("problem", problem);
        return new Result(data);
    }

    /**
     * 修改问题
     */
    @Override
    public Result editProvlem(Problem problem, String problemId) throws Exception {
        problem.setId(problemId);
        problem.setUpdateTime(new Date());
        updateById(problem);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据ID删除问题
     */
    @Override
    public Result deleteById(String problemId) throws Exception {
        removeById(problemId);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 根据条件查询问题列表
     */
    @Override
    public Result selectByParam(Problem problem) throws Exception {
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();

        wrapper.eq(!ObjectUtils.isEmpty(problem.getTitle()), "title", problem.getTitle());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getContent()), "content", problem.getContent());
        List<Problem> list = list(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        return new Result(data);
    }

    /**
     * 问题分页
     */
    @Override
    public Result selectByParamWithPage(Problem problem, int pageNo, int pageSize) throws Exception {
        IPage<Problem> page = new Page<>(pageNo, pageSize);

        QueryWrapper<Problem> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtils.isEmpty(problem.getTitle()), "title", problem.getTitle());
        wrapper.eq(!ObjectUtils.isEmpty(problem.getContent()), "content", problem.getContent());
        IPage<Problem> problemIPage = page(page, wrapper);
        return new Result(problemIPage);
    }

    /**
     * 最新问答列表
     */
    @Override
    public Result selectNewestListWithPage(String labelId, int pageNo, int pageSize) throws Exception {


        Map<String, Object> data = new HashMap<>();
        return new Result(data);
    }

    /**
     * 热门问答列表
     */
    @Override
    public Result selectHotListWithPage(String labelId, int pageNo, int pageSize) throws Exception {
        Map<String, Object> data = new HashMap<>();
        return new Result(data);
    }

    /**
     * 等待回答列表
     */
    @Override
    public Result selectWaitListWithPage(String labelId, int pageNo, int pageSize) throws Exception {
        Map<String, Object> data = new HashMap<>();
        return new Result(data);
    }

    /**
     * Problem分页
     */
    @Override
    public Result selectListParamWithPage(String labelId, int pageNo, int pageSize) throws Exception {
        Map<String, Object> data = new HashMap<>();
        return new Result(data);
    }
}
