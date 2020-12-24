package com.menu.api.query;

import com.menu.api.dto.MenuDto;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：提供权限相关的接口
 */
@Api(value = "API - QueryPermFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "menu-server", path = "/menu", contextId = "queryPerm")
public interface QueryPermFeignApi {

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "查询全部菜单列表")
    @GetMapping(value = "/menu/all/tree")
    Result<List<MenuDto>> selectPermListAll();

    /**
     * 根据菜单id查询权限列表
     *
     * @param menuIdList 菜单id
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/menu/tree/{menuIds}")
    Result<List<MenuDto>> selectPermListByMenuIds(@PathVariable(value = "menuIds") List<Integer> menuIdList);
}
