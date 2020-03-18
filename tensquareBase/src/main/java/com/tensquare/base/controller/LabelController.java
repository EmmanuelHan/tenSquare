package com.tensquare.base.controller;

import com.tensquare.base.entity.Label;
import com.tensquare.base.service.ILabelService;
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
 * @Date 2020-03-12
 */
@Slf4j
@Controller
public class LabelController {

    @Resource
    private ILabelService labelService;


    /**
     * 增加标签
     */
    @ResponseBody
    @RequestMapping(value = "/label", method = RequestMethod.POST)
    public Result addLabel(@RequestBody Label label) {
        return labelService.addLabel(label);
    }

    /**
     * 标签全部列表
     */
    @ResponseBody
    @RequestMapping(value = "/label", method = RequestMethod.GET)
    public Result getLabelList() {
        return labelService.getLabelList();
    }

    /**
     * 推荐标签列表
     */
    @ResponseBody
    @RequestMapping(value = "/label/toplist", method = RequestMethod.GET)
    public Result getLabelTopList() {
        return labelService.getLabelTopList();
    }

    /**
     * 有效标签列表
     */
    @ResponseBody
    @RequestMapping(value = "/label/list", method = RequestMethod.GET)
    public Result getNormalLabelList() {
        return labelService.getNormalLabelList();
    }

    /**
     * 根据ID查询
     */
    @ResponseBody
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    public Result getLabelById(@PathVariable String labelId) {
        return labelService.getLabelById(labelId);
    }

    /**
     * 修改标签
     */
    @ResponseBody
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.PUT)
    public Result editLabel(@PathVariable String labelId,@RequestBody Label label) {
        return labelService.editLabel(labelId,label);
    }

    /**
     * 根据ID删除
     */
    @ResponseBody
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.DELETE)
    public Result deleteLabel(@PathVariable String labelId) {
        return labelService.deleteLabel(labelId);
    }

    /**
     * 标签分页
     */
    @ResponseBody
    @RequestMapping(value = "/label/search/{pageNo}/{pageSize}", method = RequestMethod.POST)
    public Result getLabelListWithPage(@RequestBody Label label,@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
        return labelService.getLabelListWithPage(label,pageNo,pageSize);
    }

    /**
     * 查询标签列表
     */
    @ResponseBody
    @RequestMapping(value = "/label/search", method = RequestMethod.POST)
    public Result getLabelByParam(@RequestBody Label label) {
        return labelService.getLabelByParam(label);
    }


}
