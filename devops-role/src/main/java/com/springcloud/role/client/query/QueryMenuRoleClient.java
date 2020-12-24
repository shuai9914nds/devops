package com.springcloud.role.client.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springcloud.role.entity.MenuRoleRel;
import com.springcloud.role.entity.Role;
import com.springcloud.role.service.IMenuRoleRelService;
import common.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: liushuai
 * @date: 2020/10/3
 * @description：菜单角色相关
 */
@RestController
@Api(value = "API - QueryMenuRoleClient")
public class QueryMenuRoleClient {
    private static final Logger logger = LoggerFactory.getLogger(QueryMenuRoleClient.class);
    @Resource
    private IMenuRoleRelService iMenuRoleRelService;
    @Resource
    private QueryRoleClient queryRoleClient;


    /**
     * 查询用户所拥有的的权限id列表
     *
     * @return Result<List < Integer>>
     */
    @GetMapping(value = "/perm/list/{uid}")
    public Result<List<Integer>> selectPermByUid(@PathVariable("uid") Integer uid) {
        Result<List<Role>> roleResult = queryRoleClient.selectRoleListByUid(uid);
        if (!roleResult.getSuccess()) {
            logger.error("调用queryRoleClient.selectRoleListByUid服务失败，result={}", roleResult);
            return new Result<>(Collections.emptyList());
        }
        List<Role> roleList = roleResult.getObj();
        if (CollectionUtils.isEmpty(roleList)) {
            return new Result<>(Collections.emptyList());
        }
        List<Integer> roleIdList = roleList.stream().map(Role::getRoleId).distinct().collect(Collectors.toList());
        LambdaQueryWrapper<MenuRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MenuRoleRel::getId, roleIdList);
        List<MenuRoleRel> userRoleRelList = iMenuRoleRelService.list(queryWrapper);
        if (CollectionUtils.isEmpty(userRoleRelList)) {
            return new Result<>(Collections.emptyList());
        }
        List<Integer> menuIdList = userRoleRelList.stream().map(MenuRoleRel::getMenuId).distinct().collect(Collectors.toList());
        return new Result<>(menuIdList);
    }

    /**
     * 查询某个角色对应的权限id列表
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/{roleId}/menuId/list")
    public Result<List<Integer>> selectPermIdListByRoleId(@PathVariable("roleId") Integer roleId) {
        LambdaQueryWrapper<MenuRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MenuRoleRel::getRoleId, roleId);
        List<MenuRoleRel> menuRoleRelList = iMenuRoleRelService.list(queryWrapper);
        if (CollectionUtils.isEmpty(menuRoleRelList)) {
            return new Result<>(Collections.emptyList());
        }
        List<Integer> menuIdList = menuRoleRelList.stream().map(MenuRoleRel::getMenuId).distinct().collect(Collectors.toList());
        return new Result<>(menuIdList);

    }
}
