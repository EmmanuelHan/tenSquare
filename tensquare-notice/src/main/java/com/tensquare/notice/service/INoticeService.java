package com.tensquare.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tensquare.common.entity.Result;
import com.tensquare.notice.entity.Notice;
import com.tensquare.notice.entity.NoticeFresh;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
public interface INoticeService extends IService<Notice> {

    Result getNoticeById(String id);

    Result findByParam(Notice notice, Integer pageNo, Integer pageSize);

    Result saveNotice(Notice notice);

    Result updateNoticeById(Notice notice);

    Result freshPage(String userId, Integer page, Integer size);

    Result freshDelete(NoticeFresh noticeFresh);
}
