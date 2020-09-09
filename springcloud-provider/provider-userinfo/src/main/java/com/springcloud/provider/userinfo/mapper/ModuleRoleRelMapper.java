package com.springcloud.provider.userinfo.mapper;

import com.springcloud.provider.userinfo.model.ModuleRoleRel;
import com.springcloud.provider.userinfo.model.ModuleRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleRoleRelMapper {
    int countByExample(ModuleRoleRelExample example);

    int deleteByExample(ModuleRoleRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ModuleRoleRel record);

    int insertSelective(ModuleRoleRel record);

    List<ModuleRoleRel> selectByExample(ModuleRoleRelExample example);

    ModuleRoleRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ModuleRoleRel record, @Param("example") ModuleRoleRelExample example);

    int updateByExample(@Param("record") ModuleRoleRel record, @Param("example") ModuleRoleRelExample example);

    int updateByPrimaryKeySelective(ModuleRoleRel record);

    int updateByPrimaryKey(ModuleRoleRel record);
}