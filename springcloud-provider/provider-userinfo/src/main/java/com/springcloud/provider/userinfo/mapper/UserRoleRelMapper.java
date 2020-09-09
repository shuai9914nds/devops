package com.springcloud.provider.userinfo.mapper;

import com.springcloud.provider.userinfo.model.UserRoleRel;
import com.springcloud.provider.userinfo.model.UserRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelMapper {
    int countByExample(UserRoleRelExample example);

    int deleteByExample(UserRoleRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRel record);

    int insertSelective(UserRoleRel record);

    List<UserRoleRel> selectByExample(UserRoleRelExample example);

    UserRoleRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    int updateByExample(@Param("record") UserRoleRel record, @Param("example") UserRoleRelExample example);

    int updateByPrimaryKeySelective(UserRoleRel record);

    int updateByPrimaryKey(UserRoleRel record);
}