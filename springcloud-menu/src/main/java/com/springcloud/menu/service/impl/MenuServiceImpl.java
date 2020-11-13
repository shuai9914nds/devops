package com.springcloud.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.api.dto.MenuDto;
import com.springcloud.menu.entity.Menu;
import com.springcloud.menu.mapper.MenuMapper;
import com.springcloud.menu.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.Result;
import org.springframework.stereotype.Service;
import utils.BeanConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-11-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * 查询所有菜单列表
     *
     * @return
     */
    @Override
    public Result<List<MenuDto>> getMenuListAll() {
        return new Result<>(queryMenuList());
    }

    public List<MenuDto> queryMenuList() {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<MenuDto> menuDtos = BeanConverter.convertList(this.baseMapper.selectList(queryWrapper), MenuDto.class);
        // 最后的结果
        List<MenuDto> menuDtoList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < menuDtos.size(); i++) {
            // 一级菜单没有parentId
            if (0 == menuDtos.get(i).getParentId()) {
                menuDtoList.add(menuDtos.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuDto menuDto : menuDtoList) {
            menuDto.setChildMenus(getChild(menuDto.getMenuId(), menuDtos));
        }
        return menuDtoList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param menuDtos 要查找的列表
     * @return
     */
    private List<MenuDto> getChild(Integer id, List<MenuDto> menuDtos) {
        // 子菜单
        List<MenuDto> childList = new ArrayList<>();
        for (MenuDto menuDto : menuDtos) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (0 != menuDto.getParentId()) {
                if (menuDto.getParentId().equals(id)) {
                    childList.add(menuDto);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuDto menuDto : childList) {
            menuDto.setChildMenus(getChild(menuDto.getMenuId(), menuDtos));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
