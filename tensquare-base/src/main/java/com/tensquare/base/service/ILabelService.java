package com.tensquare.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.base.entity.Label;
import com.tensquare.common.entity.Result;

/**
*  代码生成器
*  service 接口
* @Author HanLei
* @Date   2020-03-12
*/
public interface ILabelService extends IService<Label> {

    /**
     * 增加标签
     * @param label
     * @return
     */
    Result addLabel(Label label);

    /**
     * 标签全部列表
     * @return
     */
    Result getLabelList();

    /**
     * 推荐标签列表
     * @return
     */
    Result getLabelTopList();

    /**
     * 获取有效标签列表
     * @return
     */
    Result getNormalLabelList();

    /**
     * 根据Id查询
     * @param labelId
     * @return
     */
    Result getLabelById(String labelId);

    /**
     * 修改标签
     * @param labelId
     * @param label
     * @return
     */
    Result editLabel(String labelId, Label label);

    /**
     * 根据ID删除标签
     * @param labelId
     * @return
     */
    Result deleteLabel(String labelId);

    /**
     * 获取列表并分页
     * @param label
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result getLabelListWithPage(Label label, Integer pageNo, Integer pageSize);

    /**
     * 根据条件查询列表
     * @param label
     * @return
     */
    Result getLabelByParam(Label label);
}
