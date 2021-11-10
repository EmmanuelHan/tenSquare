package com.tensquare.article.controller;

import com.tensquare.article.entity.Column;
import com.tensquare.article.service.IColumnService;
import com.tensquare.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author HanLei
 * @Date 2020-03-12
 */
@Slf4j
@RestController
public class ColumnController {

    @Resource
    private IColumnService columnService;

    /**
     * 增加专栏
     */
    @PostMapping("/column")
    public Result columnAdd(@RequestBody Column column) {
        return columnService.columnAdd(column);
    }

    /**
     * 专栏全部列表
     */
    @GetMapping("/column")
    public Result columnAllList() {
        return columnService.columnAllList();
    }

    /**
     * 根据ID查询专栏
     */
    @GetMapping("/column/{columnId}")
    public Result columnById(@PathVariable String columnId) {
        return columnService.columnById(columnId);
    }

    /**
     * 修改Column
     */
    @PutMapping("/column/{columnId}")
    public Result columnEdit(@PathVariable String columnId,@RequestBody Column column) {
        return columnService.columnEdit(columnId,column);
    }

    /**
     * 根据ID删除Column
     */
    @DeleteMapping("/column/{columnId}")
    public Result columnDeleteById(@PathVariable String columnId) {
        return columnService.columnDeleteById(columnId);
    }

    /**
     * 根据条件查询专栏列表
     */
    @PostMapping("/column/search")
    public Result columnByParam(@RequestBody Column column) {
        return columnService.columnByParam(column);
    }

    /**
     * 专栏分页
     */
    @PostMapping("/column/search/{pageNo}/{pageSize}")
    public Result columnByParamWithPage(@PathVariable int pageNo,@PathVariable int pageSize,@RequestBody Column column) {
        return columnService.columnByParamWithPage(pageNo,pageSize,column);
    }

    /**
     * 专栏申请
     */
    @PostMapping("/column/apply")
    public Result columnApply(@RequestBody Column column) {
        return columnService.columnApply(column);
    }

    /**
     * 申请审核
     */
    @PutMapping("/column/examine/{columnId}")
    public Result columnExamine(@PathVariable String columnId) {
        return columnService.columnExamine(columnId);
    }

    /**
     * 根据用户ID查询专栏列表
     */
    @GetMapping("/column/user/{userId}")
    public Result columnByUserId(String userId) {
        return columnService.columnByUserId(userId);
    }
}
