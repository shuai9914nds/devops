package com.menu.api.query;

import com.devops.base.common.Result;
import com.menu.api.dto.MenuDto;
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
    @ApiOperation(httpMethod = "GET", value = "查询全部权限列表")
    @GetMapping(value = "/perm/all/tree")
    Result<List<MenuDto>> selectPermListAll();

    /**
     * 根据权限id查询权限列表
     *
     * @param permIds 权限id
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/perm/tree/{permIds}")
    Result<List<MenuDto>> selectPermListByPermIds(@PathVariable("permIds") List<Integer> permIds);


}
