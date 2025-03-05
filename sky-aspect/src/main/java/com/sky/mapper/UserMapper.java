package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据 openid 查询用户.
     * @param openid 微信 openid
     * @return 用户
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入用户.
     * @param user 用户
     */
    void insert(User user);

    /**
     * 根据 id 查询用户.
     * @param id 用户 id
     * @return 用户
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getByID(Long id);
}
