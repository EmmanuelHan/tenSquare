package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.user.entity.Admin;
import com.tensquare.user.mapper.AdminMapper;
import com.tensquare.user.service.IAdminService;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.StringUtil;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public List<Admin> list() {

       return adminMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param admin  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Admin admin,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page adminPage = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

        if(admin.getId()!=null && !"".equals(admin.getId())){
            ((QueryWrapper) wrapper).eq("id",admin.getId());
        }
        if(admin.getLoginName()!=null && !"".equals(admin.getLoginName())){
            ((QueryWrapper) wrapper).eq("login_name",admin.getLoginName());
        }
        if(admin.getPassword()!=null && !"".equals(admin.getPassword())){
            ((QueryWrapper) wrapper).eq("password",admin.getPassword());
        }
        if(admin.getState()!=null && !"".equals(admin.getState())){
            ((QueryWrapper) wrapper).eq("state",admin.getState());
        }
        IPage<Admin> adminIPage = adminMapper.selectPage(adminPage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", adminPage.getTotal());
        data.put("pageNo", adminPage.getCurrent());
        data.put("list", adminIPage.getRecords());
        return new Result(data);
    }

}
