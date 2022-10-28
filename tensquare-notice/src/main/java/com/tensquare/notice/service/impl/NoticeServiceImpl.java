package com.tensquare.notice.service.impl;

import com.tensquare.notice.entity.Notice;
import com.tensquare.notice.mapper.NoticeMapper;
import com.tensquare.notice.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author hanlei
 * @since 2022-10-28
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
