package com.tensquare.notice.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.notice.entity.Notice;
import com.tensquare.notice.entity.NoticeFresh;
import com.tensquare.notice.service.INoticeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/notice/tbNotice")
public class NoticeController {

    @Resource
    private INoticeService noticeService;

    /**
     * 根据id查询消息通知
     *
     * @param id 消息编号
     * @return
     */
    @GetMapping("{id}")
    public Result selectById(@PathVariable String id) {
        return noticeService.getNoticeById(id);
    }

    /**
     * 根据条件分页查询消息通知
     * @param notice
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("search/{pageNo}/{pageSize}")
    public Result selectByList(@RequestBody Notice notice,@PathVariable Integer pageNo,@PathVariable Integer pageSize) {
        return noticeService.findByParam(notice, pageNo, pageSize);
    }

    /**
     * 新增通知
     * @param notice
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Notice notice) {
        return noticeService.saveNotice(notice);
    }

    /**
     * 修改通知
     * @param notice
     * @return
     */
    @PutMapping
    public Result updateById(@RequestBody Notice notice) {
        return noticeService.updateNoticeById(notice);
    }

    /**
     * 根据用户id查询该用户的待推送消息（新消息）
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("fresh/{userId}/{page}/{size}")
    public Result freshPage(@PathVariable String userId,
                            @PathVariable Integer page,
                            @PathVariable Integer size) {
        return noticeService.freshPage(userId, page, size);
    }

    /**
     * 删除待推送消息（新消息）
     *
     * @param noticeFresh
     * @return
     */
    @DeleteMapping("fresh")
    public Result freshDelete(@RequestBody NoticeFresh noticeFresh) {
        return noticeService.freshDelete(noticeFresh);
    }

}
