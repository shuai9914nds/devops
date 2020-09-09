package com.springcloud.provider.userinfo.mapper;

import com.springcloud.provider.userinfo.model.MenuRole;
import com.springcloud.provider.userinfo.model.MenuRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    int countByExample(MenuRoleExample example);

    int deleteByExample(MenuRoleExample example);

    int deleteByPrimaryKey(Integer moduleRoleId);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    List<MenuRole> selectByExample(MenuRoleExample example);

    MenuRole selectByPrimaryKey(Integer moduleRoleId);

    int updateByExampleSelective(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    int updateByExample(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);
}