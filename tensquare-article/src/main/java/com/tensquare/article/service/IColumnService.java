package com.tensquare.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.article.entity.Column;
import entity.Result;

/**
 * 代码生成器
 * service 接口
 *
 * @Author HanLei
 * @Date 2020-03-12
 */
public interface IColumnService extends IService<Column> {

    /**
     * 分页查询
     *
     * @param column
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(Column column, Integer page, Integer limit);

    /**
     * 增加专栏
     *
     * @param column
     * @return
     */
    Result columnAdd(Column column);

    /**
     * 专栏全部列表
     *
     * @return
     */
    Result columnAllList();

    /**
     * 根据ID查询专栏
     *
     * @param columnId
     * @return
     */
    Result columnById(String columnId);

    /**
     * 根据Id修改专栏
     *
     * @param columnId
     * @param column
     * @return
     */
    Result columnEdit(String columnId, Column column);

    /**
     * 根据ID删除专栏
     *
     * @param columnId
     * @return
     */
    Result columnDeleteById(String columnId);

    /**
     * 根据条件查询专栏列表
     *
     * @param column
     * @return
     */
    Result columnByParam(Column column);

    /**
     * 根据条件查询专栏列表并分页
     *
     * @param pageNo
     * @param pageSize
     * @param column
     * @return
     */
    Result columnByParamWithPage(int pageNo, int pageSize, Column column);

    /**
     * 申请专栏
     *
     * @param column
     * @return
     */
    Result columnApply(Column column);

    /**
     * 审核专栏
     *
     * @param columnId
     * @return
     */
    Result columnExamine(String columnId);

    /**
     * 根据用户ID查询专栏列表
     *
     * @param userId
     * @return
     */
    Result columnByUserId(String userId);
}
