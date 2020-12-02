package com.role.api;

import com.role.api.entity.Role;
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
@FeignClient(name = "role-server", path = "/role", contextId = "role")
public interface RoleFeignApi {

    /**
     * 新增一个角色
     *
     * @param role 角色实体类
     * @return Result<Role>
     */
    @PutMapping(value = "/role")
    Result<Role> addRole(@RequestBody Role role);

}
