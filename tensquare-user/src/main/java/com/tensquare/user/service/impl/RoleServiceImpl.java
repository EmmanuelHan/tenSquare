package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StringUtil;
import com.tensquare.user.entity.Role;
import com.tensquare.user.mapper.RoleMapper;
import com.tensquare.user.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date   2020-08-19
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> list() {

       return roleMapper.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param role  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(Role role,Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page<Role> rolePage = new Page<>(page,limit);
        //查询构造器
        QueryWrapper<Role> wrapper = new QueryWrapper<>();

        if(role.getId()!=null && !"".equals(role.getId())){
            wrapper.eq("id",role.getId());
        }
        if(role.getName()!=null && !"".equals(role.getName())){
            wrapper.eq("name",role.getName());
        }
        if(role.getPermission()!=null && !"".equals(role.getPermission())){
            wrapper.eq("permission",role.getPermission());
        }
        IPage<Role> roleIPage =page(rolePage, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", rolePage.getTotal());
        data.put("pageNo", rolePage.getCurrent());
        data.put("list", roleIPage.getRecords());
        return new Result(data);
    }

}
