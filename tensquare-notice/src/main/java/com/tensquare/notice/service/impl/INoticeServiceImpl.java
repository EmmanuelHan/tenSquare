package com.tensquare.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.notice.entity.Notice;
import com.tensquare.notice.entity.NoticeFresh;
import com.tensquare.notice.mapper.NoticeMapper;
import com.tensquare.notice.service.IArticleClient;
import com.tensquare.notice.service.INoticeFreshService;
import com.tensquare.notice.service.INoticeService;
import com.tensquare.notice.service.IUserClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
@Service
public class INoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Resource
    private IArticleClient articleService;

    @Resource
    private IUserClient userService;

    @Resource
    private INoticeFreshService noticeFreshService;

    @Override
    public Result findByParam(Notice notice, Integer pageNo, Integer pageSize) {
        IPage<Notice> iPage = new Page<>(pageNo,pageSize);
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>(notice);
        // TODO: 2022/11/16 有模糊查询的需要特殊处理
        IPage<Notice> page = page(iPage, queryWrapper);
        return new Result(page);
    }

    private void getInfo(Notice notice) {
        //查询用户昵称
        Result userResult = userService.selectById(notice.getOperatorId());
        HashMap userMap = (HashMap) userResult.getData();
        //设置操作者用户昵称到消息通知中
        notice.setOperatorName(userMap.get("nickname").toString());

        //查询对象名称
        Result articleResult = articleService.findById(notice.getTargetId());
        HashMap articleMap = (HashMap) articleResult.getData();
        //设置对象名称到消息通知中
        notice.setTargetName(articleMap.get("title").toString());
    }

    @Override
    public Result getNoticeById(String id) {
        Notice notice = getById(id);
        //完善消息
        getInfo(notice);
        return new Result(notice);
    }

    public IPage<Notice> selectByPage(Notice notice, Integer page, Integer size) {
        //封装分页对象
        IPage<Notice> pageData = new Page<>(page, size);
        //执行分页查询
        IPage<Notice> iPage = page(pageData, new QueryWrapper<>(notice));
        List<Notice> records = iPage.getRecords();
        //完善消息
        for (Notice n : records) {
            getInfo(n);
        }
        //返回
        return iPage;
    }

    public Result saveNotice(Notice notice) {
        //设置初始值
        //设置状态 0表示未读  1表示已读
        notice.setState("0");
        notice.setCreateTime(new Date());

        //使用分布式Id生成器，生成id
        save(notice);

        //待推送消息入库，新消息提醒
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setNoticeId(notice.getId());//消息id
        noticeFresh.setUserId(notice.getReceiverId());//待通知用户的id
        noticeFreshService.save(noticeFresh);
        return new Result();
    }

    public Result updateNoticeById(Notice notice) {
        updateById(notice);
        return new Result();
    }

    public Result freshPage(String userId, Integer page, Integer size) {
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setUserId(userId);
        IPage<NoticeFresh> pageData = new Page<>(page, size);
        IPage<NoticeFresh> noticeFreshIPage = noticeFreshService.page(pageData, new QueryWrapper<>(noticeFresh));
        return new Result(noticeFreshIPage);
    }

    public Result freshDelete(NoticeFresh noticeFresh) {
        noticeFreshService.remove(new UpdateWrapper<>(noticeFresh));
        return new Result();
    }

}
