package com.springcloud.menu.service;

import com.menu.api.dto.MenuDto;
import com.springcloud.menu.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuai
 * @since 2020-11-13
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
     * @return
     */
    List<Menu> getMenus();

    /**
     * 查询缓存中的菜单数据
     * @return
     */
    List<Menu> getMenusByCache();

    /**
     * 刷新菜单缓存
     */
    void refreshMenuCache();
}
