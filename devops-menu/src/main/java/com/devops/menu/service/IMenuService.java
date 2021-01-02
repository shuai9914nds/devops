package com.devops.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.menu.api.dto.MenuDto;
import com.menu.api.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuai
 * @since 2020-12-01
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询所有菜单列表
     *
     * @return List<MenuDto>
     */
    List<MenuDto> getMenuListAll();

    /**
     * 查询菜单列表（非树形结构）
     *
     * @return
     */
    List<Menu> getPerms();

    /**
     * 查询缓存中的菜单数据
     *
     * @return
     */
    List<Menu> getPermsByCache();

    /**
     * 刷新菜单缓存
     */
    void refreshPermCache();

    /**
     * 根据权限ids查询权限列表
     *
     * @param permIdList 权限id列表
     * @return Result<List < MenuDto>>
     */
    List<MenuDto> selectPermListByPermIds(List<Integer> permIdList);

    /**
     * 根据菜单ids查询菜单列表
     *
     * @param menuIdList 菜单id列表
     * @return Result<List < MenuDto>>
     */
    List<MenuDto> selectMenuListByMenuIds(List<Integer> menuIdList);
}
