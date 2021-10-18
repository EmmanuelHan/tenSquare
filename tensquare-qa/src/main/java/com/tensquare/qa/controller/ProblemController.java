package com.tensquare.qa.controller;

import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.entity.ResultEnum;
import com.tensquare.qa.service.IProblemService;
import entity.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import system.Constants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Controller
public class ProblemController {

    @Resource
    private IProblemService  problemService;

    @Resource
    private HttpServletRequest request;

    /**
     * 增加问题
     */
    @PostMapping("/problem")
    public Result problemAdd(@RequestBody Problem problem) throws Exception{
        Claims claims = (Claims) request.getAttribute(Constants.USER_CLAIMS);
        if(ObjectUtils.isEmpty(claims)){
            return new Result(ResultEnum.ACCESS_DENIED);
        }
        problem.setUserId(claims.getId());
        return problemService.addProblem(problem);
    }

    /**
     * Problem全部列表
     */
    @GetMapping("/problem")
    public Result problemAllList() throws Exception{
        return problemService.cityList();
    }

    /**
     * 根据ID查询问题
     */
    @GetMapping("/problem/{problemId}")
    public Result problemSelectById(@PathVariable String problemId) throws Exception{
        return problemService.selectById(problemId);
    }

    /**
     * 修改问题
     */
    @PutMapping("/problem/{problemId}")
    public Result problemEdit(@RequestBody Problem problem,@PathVariable String problemId) throws Exception{
        return problemService.editProvlem(problem,problemId);
    }

    /**
     * 根据ID删除问题
     */
    @DeleteMapping("/problem/{problemId}")
    public Result problemDelete(@PathVariable String problemId) throws Exception{
        return problemService.deleteById(problemId);
    }

    /**
     * 根据条件查询问题列表
     */
    @PostMapping("/problem/search")
    public Result problemSearch(@RequestBody Problem problem) throws Exception{
        return problemService.selectByParam(problem);
    }

    /**
     * 问题分页
     */
    @PostMapping("/problem/search/{pageNo}/{pageSize}")
    public Result problemSearchWithPage(@RequestBody Problem problem,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectByParamWithPage(problem,pageNo,pageSize);
    }

    /**
     * 最新问答列表
     */
    @GetMapping("/problem/newList/{labelId}/{pageNo}/{pageSize}")
    public Result problemNewestListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectNewestListWithPage(labelId,pageNo,pageSize);
    }

    /**
     * 热门问答列表
     */
    @GetMapping("/problem/hotList/{labelId}/{page}/{size}")
    public Result problemHotListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectHotListWithPage(labelId,pageNo,pageSize);
    }

    /**
     * 等待回答列表
     */
    @GetMapping("/problem/waitlist/{label}/{page}/{size}")
    public Result problemWaitListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectWaitListWithPage(labelId,pageNo,pageSize);
    }

    /**
     * Problem分页
     */
    @PostMapping("/problem/all/{label}/{page}/{size}")
    public Result problemListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectListParamWithPage(labelId,pageNo,pageSize);
    }

 }
