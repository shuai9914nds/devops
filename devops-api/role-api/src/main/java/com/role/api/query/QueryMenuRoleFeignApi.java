package com.role.api.query;

import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryUserInfoFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "role-server", path = "role", contextId = "queryMenuRole")
public interface QueryMenuRoleFeignApi {

    /**
     * 查询用户所拥有的的权限id列表
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/perm/list/{uid}")
    Result<List<Integer>> selectPermByUid(@PathVariable("uid") Integer uid);

    /**
     * 查询某个角色对应的权限id列表
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/{roleId}/menuId/list")
    Result<List<Integer>> selectPermIdListByRoleId(@PathVariable("roleId") Integer roleId);
}
