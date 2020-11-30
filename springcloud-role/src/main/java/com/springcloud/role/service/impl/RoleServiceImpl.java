package com.springcloud.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.role.entity.Role;
import com.springcloud.role.mapper.RoleMapper;
import com.springcloud.role.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-11-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    /**
     * 查询一个用户信息
     *
     * @param role 查询条件
     * @return role
     */
    @Override
    public Role getOneByCondition(Role role) {
        return this.baseMapper.selectOne(getLambdaQueryWrapper(role));
    }

    /**
     * 查询角色列表
     * @param role 角色对象
     * @return List<Role>
     */
    @Override
    public List<Role> selectUserList(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = getLambdaQueryWrapper(role);
        return this.baseMapper.selectList(queryWrapper);
    }


    /**
     * 获取LambdaQueryWrapper<Role>
     *
     * @param role 用户信息
     * @return LambdaQueryWrapper<Role>
     */
    private LambdaQueryWrapper<Role> getLambdaQueryWrapper(Role role) {
        LambdaQueryWrapper<Role> queryMapper = new LambdaQueryWrapper<>();
        if (null == role) {
            return null;
        }
        if (null != role.getRoleId()) {
            queryMapper.eq(Role::getRoleId, role.getRoleId());
        }
        if (null != role.getIsAvailable()) {
            queryMapper.eq(Role::getIsAvailable, role.getIsAvailable());
        }
        if (StringUtils.isNotBlank(role.getRoleIdentify())) {
            queryMapper.eq(Role::getRoleIdentify, role.getRoleIdentify());
        }
        if (StringUtils.isNotBlank(role.getRoleName())) {
            queryMapper.eq(Role::getRoleName,role.getRoleName());
        }
        return queryMapper;
    }
}
