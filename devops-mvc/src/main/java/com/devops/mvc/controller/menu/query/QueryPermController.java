package com.devops.mvc.controller.menu.query;

import com.devops.base.common.Result;
import com.menu.api.dto.MenuDto;
import com.menu.api.query.QueryPermFeignApi;
import com.role.api.entity.Role;
import com.role.api.query.QueryMenuRoleFeignApi;
import com.role.api.query.QueryRoleFeignApi;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：权限相关的controller
 */
@RestController
@Api(value = "API - QueryMenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryPermController {

    private static final Logger logger = LoggerFactory.getLogger(QueryPermController.class);


    @Resource
    private QueryPermFeignApi queryPermFeignApi;
    @Resource
    private QueryMenuRoleFeignApi queryMenuRoleFeignApi;
    @Resource
    private QueryRoleFeignApi queryRoleFeignApi;


    /**
     * 查询所有权限，返回树结构
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/mvc/perm/all/tree")
    public Result<List<MenuDto>> selectPermListAll() {
        return queryPermFeignApi.selectPermListAll();
    }

    /**
     * 查询用户所拥有的的权限
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/mvc/perm/list/{uid}")
    public Result<List<MenuDto>> selectPermByUid(@PathVariable("uid") Integer uid) {
        //查询用户拥有的权限id列表
        Result<List<Integer>> menuRoleResult = queryMenuRoleFeignApi.selectPermByUid(uid);
        if (!menuRoleResult.getSuccess()) {
            logger.error("调用接口queryMenuRoleFeignApi.selectPermByUid失败,result={}", menuRoleResult);
            return new Result<>(Collections.emptyList());
        }
        List<Integer> menuIdList = menuRoleResult.getObj();
        if (CollectionUtils.isEmpty(menuIdList)) {
            return new Result<>(Collections.emptyList());
        }
        Result<List<MenuDto>> menuResult = queryPermFeignApi.selectPermListByMenuIds(menuIdList);
        if (!menuResult.getSuccess()) {
            logger.error("调用queryPermFeignApi.selectPermListByMenuIds接口失败，result={}", menuResult);
        }
        return menuResult;
    }

    /**
     * 查询某个角色对应的权限id列表
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/mvc/{roleId}/perm/list")
    Result<List<MenuDto>> selectPermListByRoleId(@PathVariable("roleId") Integer roleId) {
        Result<List<Integer>> roleResult = queryMenuRoleFeignApi.selectPermIdListByRoleId(roleId);
        if (!roleResult.getSuccess()) {
            logger.error("调用接口queryMenuRoleFeignApi.selectPermIdListByRoleId失败,result={}", roleResult);
            return new Result<>(Collections.emptyList());
        }
        List<Integer> menuIdList = roleResult.getObj();
        if (CollectionUtils.isEmpty(menuIdList)) {
            return new Result<>(Collections.emptyList());
        }
        Result<List<MenuDto>> menuResult = queryPermFeignApi.selectPermListByMenuIds(menuIdList);
        if (!menuResult.getSuccess()) {
            logger.error("调用queryPermFeignApi.selectPermListByMenuIds接口失败，result={}", menuResult);
        }
        return menuResult;
    }

    /**
     * 查询某个角色对应的权限id列表
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/mvc/perm/map/{roleId}")
    Result<Map<String, Object>> selectPermIdListByRoleId(@PathVariable("roleId") Integer roleId) {
        Result<List<Integer>> menuRoleResult = queryMenuRoleFeignApi.selectPermIdListByRoleId(roleId);
        if (!menuRoleResult.getSuccess()) {
            logger.error("调用接口queryMenuRoleFeignApi.selectPermIdListByRoleId失败,result={}", menuRoleResult);
            return new Result<>(Collections.emptyMap());
        }
        List<Integer> permIdList = menuRoleResult.getObj();
        Map<String, Object> map = new HashMap<>();

        if (!CollectionUtils.isEmpty(permIdList)) {
            map.put("permIdList", permIdList);
        }
        Result<Role> roleResult = queryRoleFeignApi.selectOne(roleId, "");
        if (!roleResult.getSuccess()) {
            logger.error("调用接口queryRoleFeignApi.selectOne失败,result={}", roleResult);
        }
        Role role = roleResult.getObj();
        map.put("role", role);
        return new Result<>(map);
    }
}
