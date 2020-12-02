package com.springcloud.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.menu.entity.MenuRoleRel;
import com.springcloud.menu.mapper.MenuMapper;
import com.springcloud.menu.mapper.MenuRoleRelMapper;
import com.springcloud.menu.service.IMenuRoleRelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-11-13
 */
@Service
public class MenuRoleRelServiceImpl extends ServiceImpl<MenuRoleRelMapper, MenuRoleRel> implements IMenuRoleRelService {

    @Resource
    private MenuMapper menuMapper;


    /**
     * 获取LambdaQueryWrapper<MenuRoleRel>
     *
     * @param menuRoleRel menuRoleRel
     * @return LambdaQueryWrapper<MenuRoleRel>
     */
    private LambdaQueryWrapper<MenuRoleRel> getLambdaQueryWrapper(MenuRoleRel menuRoleRel) {
        if (null == menuRoleRel) {
            return null;
        }
        LambdaQueryWrapper<MenuRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        if (null != menuRoleRel.getId()) {
            queryWrapper.eq(MenuRoleRel::getId, menuRoleRel.getId());
        }
        if (null != menuRoleRel.getMenuId()) {
            queryWrapper.eq(MenuRoleRel::getMenuId, menuRoleRel.getMenuId());
        }
        if (null != menuRoleRel.getRoleId()) {
            queryWrapper.eq(MenuRoleRel::getRoleId, menuRoleRel.getRoleId());
        }
        if (StringUtils.isNotBlank(menuRoleRel.getCreateBy())) {
            queryWrapper.eq(MenuRoleRel::getCreateBy, menuRoleRel.getCreateBy());
        }
        if (StringUtils.isNotBlank(menuRoleRel.getUpdateBy())) {
            queryWrapper.eq(MenuRoleRel::getUpdateBy, menuRoleRel.getUpdateBy());
        }
        return queryWrapper;

    }
}
