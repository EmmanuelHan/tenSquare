package com.tensquare.qa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.qa.entity.Problem;
import entity.Result;

/**
 * 代码生成器
 * service 接口
 *
 * @Author HanLei
 * @Date 2020-03-12
 */
public interface IProblemService extends IService<Problem> {

    /**
     * 分页查询
     *
     * @param problem
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(Problem problem, Integer page, Integer limit);

    /**
     * 增加问题
     */
    Result addCity(Problem problem) throws Exception;

    /**
     * Problem全部列表
     */
    Result cityList() throws Exception;

    /**
     * 根据ID查询问题
     */
    Result selectById(String problemId) throws Exception;

    /**
     * 修改问题
     */
    Result editProvlem(Problem problem, String problemId) throws Exception;

    /**
     * 根据ID删除问题
     */
    Result deleteById(String problemId) throws Exception;

    /**
     * 根据条件查询问题列表
     */
    Result selectByParam(Problem problem) throws Exception;

    /**
     * 问题分页
     */
    Result selectByParamWithPage(Problem problem, int pageNo, int pageSize) throws Exception;

    /**
     * 最新问答列表
     */
    Result selectNewestListWithPage(String labelId, int pageNo, int pageSize) throws Exception;

    /**
     * 热门问答列表
     */
    Result selectHotListWithPage(String labelId, int pageNo, int pageSize) throws Exception;

    /**
     * 等待回答列表
     */
    Result selectWaitListWithPage(String labelId, int pageNo, int pageSize) throws Exception;

    /**
     * Problem分页
     */
    Result selectListParamWithPage(String labelId, int pageNo, int pageSize) throws Exception;

}
