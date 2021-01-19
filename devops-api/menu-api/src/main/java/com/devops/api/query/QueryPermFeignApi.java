package com.devops.api.query;

import com.devops.api.dto.MenuDto;
import com.devops.api.entity.Menu;
import com.devops.base.common.Result;
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
@FeignClient(name = "menu", path = "/menu", contextId = "queryPerm")
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
     * 根据权限id查询权限列表(树结构)
     *
     * @param permIds 权限id
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/perm/tree/{permIds}")
    Result<List<MenuDto>> selectPermTreeByPermIds(@PathVariable("permIds") List<Integer> permIds);

    /**
     * 查询某个用户的权限列表
     *
     * @param uid 用户id
     * @return Result<List < Menu>>
     */
    @GetMapping(value = "/perm/list/{uid}")
    Result<List<Menu>> selectPermListByUid(@PathVariable("uid") Integer uid);


}
