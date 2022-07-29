package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StringUtil;
import com.tensquare.user.entity.Follow;
import com.tensquare.user.mapper.FollowMapper;
import com.tensquare.user.service.IFollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date   2020-03-17
 */
@Slf4j
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    @Resource
    private FollowMapper followMapper;

    @Override
    public List<Follow> list() {

       return followMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param follow  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Follow follow, Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page<Follow> followPage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();

        if(follow.getUserId()!=null && !"".equals(follow.getUserId())){
            wrapper.eq("user_id",follow.getUserId());
        }
        if(follow.getTargetUser()!=null && !"".equals(follow.getTargetUser())){
            wrapper.eq("target_user",follow.getTargetUser());
        }
        IPage<Follow> followIPage = page(followPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", followPage.getTotal());
        data.put("pageNo", followPage.getCurrent());
        data.put("list", followIPage.getRecords());
        return new Result(data);
    }

}
