package com.springcloud.provider.userinfo.mapper;

import com.springcloud.provider.userinfo.model.ModuleRole;
import com.springcloud.provider.userinfo.model.ModuleRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleRoleMapper {
    int countByExample(ModuleRoleExample example);

    int deleteByExample(ModuleRoleExample example);

    int deleteByPrimaryKey(Integer moduleRoleId);

    int insert(ModuleRole record);

    int insertSelective(ModuleRole record);

    List<ModuleRole> selectByExample(ModuleRoleExample example);

    ModuleRole selectByPrimaryKey(Integer moduleRoleId);

    int updateByExampleSelective(@Param("record") ModuleRole record, @Param("example") ModuleRoleExample example);

    int updateByExample(@Param("record") ModuleRole record, @Param("example") ModuleRoleExample example);

    int updateByPrimaryKeySelective(ModuleRole record);

    int updateByPrimaryKey(ModuleRole record);
}