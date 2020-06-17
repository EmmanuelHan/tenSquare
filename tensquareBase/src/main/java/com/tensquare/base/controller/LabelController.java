package com.tensquare.base.controller;

import com.tensquare.base.entity.Label;
import com.tensquare.base.service.ILabelService;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@CrossOrigin
@RestController
public class LabelController {

    @Resource
    private ILabelService labelService;


    /**
     * 增加标签
     */
    @PostMapping("/label")
    public Result addLabel(@RequestBody Label label) {
        return labelService.addLabel(label);
    }

    /**
     * 标签全部列表
     */
    @GetMapping("/label")
    public Result getLabelList() {
        return labelService.getLabelList();
    }

    /**
     * 推荐标签列表
     */
    @GetMapping("/label/toplist")
    public Result getLabelTopList() {
        return labelService.getLabelTopList();
    }

    /**
     * 有效标签列表
     */
    @GetMapping("/label/list")
    public Result getNormalLabelList() {
        return labelService.getNormalLabelList();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/label/{labelId}")
    public Result getLabelById(@PathVariable String labelId) {
        return labelService.getLabelById(labelId);
    }

    /**
     * 修改标签
     */
    @PutMapping("/label/{labelId}")
    public Result editLabel(@PathVariable String labelId, @RequestBody Label label) {
        return labelService.editLabel(labelId, label);
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/label/{labelId}")
    public Result deleteLabel(@PathVariable String labelId) {
        return labelService.deleteLabel(labelId);
    }

    /**
     * 标签分页
     */
    @PostMapping("/label/search/{pageNo}/{pageSize}")
    public Result getLabelListWithPage(@RequestBody Label label, @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return labelService.getLabelListWithPage(label, pageNo, pageSize);
    }

    /**
     * 查询标签列表
     */
    @PostMapping("/label/search")
    public Result getLabelByParam(@RequestBody Label label) {
        return labelService.getLabelByParam(label);
    }


}
