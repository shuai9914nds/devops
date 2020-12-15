package com.role.api;

import common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - UserRoleFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "role-server", path = "/role", contextId = "userRole")
public interface UserRoleFeignApi {


    /**
     * 根据用户id或者角色id删除关联关系
     *
     * @param roleId 角色id
     * @param uid    用户id
     * @return Result<Void>
     */
    @DeleteMapping("/user/role")
    Result<Void> deleteUserRole(@RequestParam(value = "roleId", required = false) Integer roleId,
                                @RequestParam(value = "uid", required = false) Integer uid);


    /**
     * 为用户添加角色
     *
     * @param roleId 角色id
     * @param uid    用户id
     * @return Result<Void>
     */
    @PutMapping("/user/role")
    Result<Void> addUserRole(@RequestParam(value = "roleId") Integer roleId,
                             @RequestParam(value = "uid") Integer uid);
}
