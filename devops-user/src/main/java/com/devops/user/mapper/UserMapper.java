package com.devops.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shuai
 * @since 2020-11-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
