package com.devops.api.query;

import com.devops.api.dto.MenuDto;
import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：提供菜单相关的接口
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "menu", path = "/menu", contextId = "queryMenu")
public interface QueryMenuFeignApi {

    /**
     * 根据菜单ids查询菜单列表
     *
     * @param menuIds 菜单id列表
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/menu/tree")
    Result<List<MenuDto>> selectMenuListByMenuIds(@RequestParam(value = "menuIds", required = false) List<Integer> menuIds);
}
