package com.devops.role.client.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devops.api.entity.MenuRoleRel;
import com.devops.api.entity.Role;
import com.devops.base.common.Result;
import com.devops.role.service.IMenuRoleRelService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
 * @description：角色权限相关
 */
@Slf4j
@RestController
@Api(value = "API - QueryPermRoleClient")
public class QueryPermRoleClient {
    @Resource
    private IMenuRoleRelService iMenuRoleRelService;
    @Resource
    private QueryRoleClient queryRoleClient;


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

    /**
     * 查询用户所拥有的的权限id列表
     *
     * @return Result<List < Integer>>
     */
    @GetMapping(value = "/perm/id/list/{uid}")
    public Result<List<Integer>> selectPermByUid(@PathVariable("uid") Integer uid) {
        Result<List<Role>> roleResult = queryRoleClient.selectRoleListByUid(uid);
        if (!roleResult.getSuccess()) {
            log.error("调用queryRoleClient.selectRoleListByUid服务失败，result={}", roleResult);
            return new Result<>(Collections.emptyList());
        }
        List<Role> roleList = roleResult.getObj();
        if (CollectionUtils.isEmpty(roleList)) {
            return new Result<>(Collections.emptyList());
        }
        List<Integer> roleIdList = roleList.stream().map(Role::getRoleId).distinct().collect(Collectors.toList());
        LambdaQueryWrapper<MenuRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MenuRoleRel::getRoleId, roleIdList);
        List<MenuRoleRel> userRoleRelList = iMenuRoleRelService.list(queryWrapper);
        if (CollectionUtils.isEmpty(userRoleRelList)) {
            return new Result<>(Collections.emptyList());
        }
        List<Integer> menuIdList = userRoleRelList.stream().map(MenuRoleRel::getMenuId).distinct().collect(Collectors.toList());
        return new Result<>(menuIdList);
    }
}
