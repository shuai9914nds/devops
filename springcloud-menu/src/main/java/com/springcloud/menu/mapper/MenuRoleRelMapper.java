package com.springcloud.menu.mapper;

import com.springcloud.menu.entity.MenuRoleRel;
import com.springcloud.menu.entity.MenuRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleRelMapper {
    int countByExample(MenuRoleRelExample example);

    int deleteByExample(MenuRoleRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuRoleRel record);

    int insertSelective(MenuRoleRel record);

    List<MenuRoleRel> selectByExample(MenuRoleRelExample example);

    MenuRoleRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuRoleRel record, @Param("example") MenuRoleRelExample example);

    int updateByExample(@Param("record") MenuRoleRel record, @Param("example") MenuRoleRelExample example);

    int updateByPrimaryKeySelective(MenuRoleRel record);

    int updateByPrimaryKey(MenuRoleRel record);
}