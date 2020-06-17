package com.tensquare.qa.controller;

import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.service.IProblemService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Resource
    private IProblemService  problemService;

    /**
     * 增加问题
     */
    @PostMapping("/")
    public Result problemAdd(@RequestBody Problem problem) throws Exception{
        return problemService.addCity(problem);
    }

    /**
     * Problem全部列表
     */
    @GetMapping("/")
    public Result problemAllList() throws Exception{
        return problemService.cityList();
    }

    /**
     * 根据ID查询问题
     */
    @GetMapping("/{problemId}")
    public Result problemSelectById(@PathVariable String problemId) throws Exception{
        return problemService.selectById(problemId);
    }

    /**
     * 修改问题
     */
    @PutMapping("/{problemId}")
    public Result problemEdit(@RequestBody Problem problem,@PathVariable String problemId) throws Exception{
        return problemService.editProvlem(problem,problemId);
    }

    /**
     * 根据ID删除问题
     */
    @DeleteMapping("/{problemId}")
    public Result problemDelete(@PathVariable String problemId) throws Exception{
        return problemService.deleteById(problemId);
    }

    /**
     * 根据条件查询问题列表
     */
    @PostMapping("/search")
    public Result problemSearch(@RequestBody Problem problem) throws Exception{
        return problemService.selectByParam(problem);
    }

    /**
     * 问题分页
     */
    @PostMapping("/search/{pageNo}/{pageSize}")
    public Result problemSearchWithPage(@RequestBody Problem problem,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectByParamWithPage(problem,pageNo,pageSize);
    }

    /**
     * 最新问答列表
     */
    @GetMapping("/newList/{labelId}/{pageNo}/{pageSize}")
    public Result problemNewestListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectNewestListWithPage(labelId,pageNo,pageSize);
    }

    /**
     * 热门问答列表
     */
    @GetMapping("/hotList/{labelId}/{page}/{size}")
    public Result problemHotListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectHotListWithPage(labelId,pageNo,pageSize);
    }

    /**
     * 等待回答列表
     */
    @GetMapping("/waitlist/{label}/{page}/{size}")
    public Result problemWaitListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectWaitListWithPage(labelId,pageNo,pageSize);
    }

    /**
     * Problem分页
     */
    @PostMapping("/all/{label}/{page}/{size}")
    public Result problemListWithPage(@PathVariable String labelId,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
        return problemService.selectListParamWithPage(labelId,pageNo,pageSize);
    }

 }
