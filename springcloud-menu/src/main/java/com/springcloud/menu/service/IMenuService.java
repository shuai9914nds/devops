package com.springcloud.menu.service;

import com.menu.api.dto.MenuDto;
import com.springcloud.menu.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import common.Result;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuai
 * @since 2020-11-12
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 查询所有菜单列表
     *
     * @return
     */
    Result<List<MenuDto>> getMenuListAll();
}
