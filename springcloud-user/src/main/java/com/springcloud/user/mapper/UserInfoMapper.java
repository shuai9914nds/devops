package com.springcloud.user.mapper;

import com.springcloud.user.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shuai
 * @since 2020-10-02
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
