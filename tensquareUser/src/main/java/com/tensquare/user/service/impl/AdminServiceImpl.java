package com.tensquare.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.user.entity.Admin;
import com.tensquare.user.entity.ResultEnum;
import com.tensquare.user.mapper.AdminMapper;
import com.tensquare.user.service.IAdminService;
import entity.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import system.Constants;
import util.JwtUtil;
import util.StringUtil;
import util.Type;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HanLei
 * @Date 2020-03-17
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private HttpServletRequest request;

    @Override
    public List<Admin> list() {

        return adminMapper.selectList(null);
    }

    /**
     * 查询是否存在相同用户名
     *
     * @param loginName
     * @return turn-存在 false-不存在
     */
    private boolean isExist(String loginName) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginName);
        wrapper.eq("state", Type.STATE_NORMAL);
        int count = count(wrapper);
        return count > 0;

    }

    /**
     * 增加管理员
     *
     * @param admin
     * @return
     */
    @Override
    public Result addAdmin(Admin admin) {
        if (isExist(admin.getLoginName())) {
            return new Result(ResultEnum.ADMIN_SAME_NAME);
        } else {
            admin.setPassword(encoder.encode(admin.getPassword()));
            save(admin);
            return new Result(ResultEnum.SUCCESS);
        }
    }

    /**
     * 管理员全部列表
     *
     * @return
     */
    @Override
    public Result getAdminList() {
        List<Admin> list = list();
        return new Result(list);
    }


    /**
     * 根据ID查询
     *
     * @param adminId
     * @return
     */
    @Override
    public Result getAdminById(String adminId) {
        Admin byId = getById(adminId);
        return new Result(byId);
    }

    /**
     * 编辑管理员
     *
     * @param adminId
     * @param admin
     * @return
     */
    @Override
    public Result editAdmin(String adminId, Admin admin) {
        if (isExist(admin.getLoginName())) {
            return new Result(ResultEnum.ADMIN_SAME_NAME);
        } else {
            admin.setId(adminId);
            admin.setPassword(encoder.encode(admin.getPassword()));
            updateById(admin);
            return new Result(ResultEnum.SUCCESS);
        }
    }

    /**
     * 根据ID删除
     *
     * @param adminId
     * @return
     */
    @Override
    public Result deleteAdminById(String adminId) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return new Result(ResultEnum.ACCESS_DENIED);
        }
        if (!authorization.startsWith(Constants.AUTH_START)) {
            return new Result(entity.ResultEnum.ACCESS_DENIED);
        }
        String token = authorization.substring(Constants.AUTH_START.length());//提取token
        Claims claims = jwtUtil.parseJWT(token);
        if(ObjectUtils.isEmpty(claims)){
            return new Result(entity.ResultEnum.ACCESS_DENIED);
        }
        if (!Constants.ROLE_ADMIN.equals(claims.get(Constants.NAME_ROLE))) {
            return new Result(entity.ResultEnum.ACCESS_DENIED);
        }
        removeById(adminId);
        return new Result();
    }

    /**
     * 管理员分页
     *
     * @param pageNo
     * @param pageSize
     * @param admin
     * @return
     */
    @Override
    public Result getAdminListByParamWithPage(Integer pageNo, Integer pageSize, Admin admin) {
        if (pageNo == null) {
            pageNo = StringUtil.START_PAGE;
        }
        if (pageSize == null) {
            pageSize = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page<Admin> adminPage = new Page<>(pageNo, pageSize);
        //查询构造器
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();

        if (!ObjectUtils.isEmpty(admin.getLoginName())) {
            wrapper.like("login_name", admin.getLoginName());
        }
        if (!ObjectUtils.isEmpty(admin.getState())) {
            wrapper.eq("state", admin.getState());
        }
        IPage<Admin> adminIPage = page(adminPage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pageSize", pageSize);
        data.put("total", adminPage.getTotal());
        data.put("pageNo", adminPage.getCurrent());
        data.put("list", adminIPage.getRecords());
        return new Result(data);
    }

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    @Override
    public Result adminLogin(Admin admin) {

        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", admin.getLoginName());
        wrapper.eq("state", Type.STATE_NORMAL);
        Admin one = getOne(wrapper);
        if (!ObjectUtils.isEmpty(one) && encoder.matches(admin.getPassword(), one.getPassword())) {
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginName(), Constants.ROLE_ADMIN);
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put(Constants.NAME_ROLE, Constants.ROLE_ADMIN);
            return new Result(data);
        }
        return new Result(ResultEnum.ACCESS_WRONG);
    }
}
