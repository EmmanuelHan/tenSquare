package com.tensquare.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 代码生成器
 * Mapper 接口
 * @Author HanLei
 * @Date   2020-03-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void updateFansCount(@Param("id") String id, @Param("type") int type);

    void updateFollowCount(@Param("id") String id, @Param("type") int type);

}
