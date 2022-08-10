package com.tensquare.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.auth.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码生成器
 * Mapper 接口
 * @Author HanLei
 * @Date   2020-08-19
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectPermissionByUserId(@Param("id") String id);

}
