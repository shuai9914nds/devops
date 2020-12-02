package com.menu.api;

import com.menu.api.dto.MenuRoleDto;
import common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "menu-server", path = "/menu", contextId = "menuRole")
public interface MenuRoleFeignApi {

    /**
     * 新增角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色实体类
     * @return Result<Void>
     */
    @PutMapping("/menu/role")
    Result<Void> addMenuRole(@RequestBody MenuRoleDto menuRoleDto);
}
