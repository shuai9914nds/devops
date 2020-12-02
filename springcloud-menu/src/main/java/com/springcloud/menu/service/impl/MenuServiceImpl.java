package com.springcloud.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.menu.api.dto.MenuDto;
import com.springcloud.menu.entity.Menu;
import com.springcloud.menu.mapper.MenuMapper;
import com.springcloud.menu.service.IMenuService;
import com.springcloud.menu.util.JsonUtils;
import common.Constant;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.BeanConverter;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-12-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单列表(树形结构)
     *
     * @return List<MenuDto>
     */
    @Override
    public List<MenuDto> getMenuListAll() {
        return queryPermList();
    }

    /**
     * 查询菜单列表（非树形结构）
     *
     * @return List<Menu>
     */
    @Override
    public List<Menu> getPerms() {
        Menu menu = new Menu();
        menu.setMenuId(4);
        List<Menu> menus = this.baseMapper.selectList(null);
        if (CollectionUtils.isEmpty(menus)) {
            return menus;
        }
        return menus.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 根据菜单id查询权限列表
     *
     * @param menuIdList 菜单id
     * @return Result<List < MenuDto>>
     */
    public List<MenuDto> selectPermListByMenuIds(List<Integer> menuIdList) {
        List<Menu> menus = this.baseMapper.selectBatchIds(menuIdList);
        return getPermTree(menus);
    }

    /**
     * 查询缓存中的菜单数据（非树形结构）
     *
     * @return
     */
    @Override
    public List<Menu> getPermsByCache() {
        RBucket<Object> bucket = redissonClient.getBucket(Constant.REDIS_ALL_MENUS_KEY);
        Object o = bucket.get();
        if (null == o) {
            List<Menu> menus = getPerms();
            try {
                bucket.set(JsonUtils.toJson(menus));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return menus;
        } else {
            try {
                return JsonUtils.fromListJson(o.toString(), List.class, Menu.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }


    /**
     * 刷新菜单缓存
     */
    @Override
    public void refreshPermCache() {
        RBucket<Object> bucket = redissonClient.getBucket(Constant.REDIS_ALL_MENUS_KEY);
        bucket.delete();
        List<Menu> menus = getPerms();
        if (CollectionUtils.isEmpty(menus)) {
            bucket.set("");
        } else {
            try {
                bucket.set(JsonUtils.toJson(menus));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<MenuDto> queryPermList() {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Menu> menus = this.baseMapper.selectList(queryWrapper);
        return getPermTree(menus);
    }

    /**
     * 获取权限树结构
     *
     * @param menus
     * @return
     */
    private List<MenuDto> getPermTree(List<Menu> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        List<Menu> menuList = menus.stream().distinct().collect(Collectors.toList());
        List<MenuDto> menuDtos = BeanConverter.convertList(menuList, MenuDto.class);
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
            menuDto.setChildren(getChild(menuDto.getMenuId(), menuDtos));
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
            menuDto.setChildren(getChild(menuDto.getMenuId(), menuDtos));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }


    /**
     * 获取LambdaQueryWrapper<Menu>
     *
     * @param menu 菜单
     * @return LambdaQueryWrapper<Menu>
     */
    private LambdaQueryWrapper<Menu> getLambdaQueryWrapper(Menu menu) {
        if (null == menu) {
            return null;
        }
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (null == menu.getMenuId()) {
            wrapper.eq(Menu::getMenuId, menu.getMenuId());
        }
        if (null == menu.getParentId()) {
            wrapper.eq(Menu::getParentId, menu.getParentId());
        }
        if (StringUtils.isNotBlank(menu.getPermission())) {
            wrapper.eq(Menu::getPermission, menu.getPermission());
        }
        if (null != menu.getResourceType()) {
            wrapper.eq(Menu::getResourceType, menu.getResourceType());
        }
        if (null != menu.getIsShow()) {
            wrapper.eq(Menu::getIsShow, menu.getIsShow());
        }
        if (StringUtils.isNotBlank(menu.getIcon())) {
            wrapper.eq(Menu::getIcon, menu.getIcon());
        }
        if (StringUtils.isNotBlank(menu.getComponent())) {
            wrapper.eq(Menu::getComponent, menu.getComponent());
        }
        return wrapper;
    }
}
