package com.devops.api;

import com.devops.api.entity.Role;
import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "role", path = "/role", contextId = "role")
public interface RoleFeignApi {

    /**
     * 新增一个角色
     *
     * @param role 角色实体类
     * @return Result<Role>
     */
    @PutMapping(value = "/role")
    Result<Role> addRole(@RequestBody Role role);

    /**
     * 删除一个角色
     *
     * @param roleId 角色id
     * @return Result<Void>
     */
    @DeleteMapping(value = "/role")
    Result<Void> deleteRole(@RequestParam(value = "roleId") Integer roleId);

    /**
     * 更新一个角色
     *
     * @param roleId 角色id
     * @return Result<Void>
     */
    @PostMapping(value = "/role")
    Result<Void> updateRole(@RequestParam(value = "roleId") Integer roleId, @RequestParam(value = "roleName", required = false) String roleName);

}
