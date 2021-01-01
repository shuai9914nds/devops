package com.devops.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.role.api.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
* <p>
 *  Mapper 接口
 * </p>
*
* @author shuai
* @since 2020-11-29
*/
@Mapper
 public interface RoleMapper extends BaseMapper<Role> {

 }
