package com.role.api;

import com.devops.base.common.Result;
import com.role.api.dto.MenuRoleDto;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "role-server", path = "role", contextId = "permRole")
public interface PermRoleFeignApi {

    /**
     * 新增角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色实体类
     * @return Result<Void>
     */
    @PutMapping("/menu/role")
    Result<Void> addMenuRole(@RequestBody MenuRoleDto menuRoleDto);

    /**
     * 新增角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色实体类
     * @return Result<Void>
     */
    @PostMapping("/menu/role")
    Result<Void> updateMenuRole(@RequestBody MenuRoleDto menuRoleDto);
}
