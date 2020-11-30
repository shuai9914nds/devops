package com.user.api.query;

import com.user.api.entity.UserRoleRel;
import common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryUserInfoFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "user-server", path = "user", contextId = "queryUserRole")
public interface QueryUserRoleFeignApi {

    /**
     * 根据用户id差列表查询用户角色关联关系
     *
     * @param uids 用户id烈烈表
     * @return key为uid value为这个用户的角色id列表
     */
    @GetMapping(value = "/user/role/id/map")
    Result<Map<Integer, List<Integer>>> selectUserRoleIdsByUids(@RequestParam("uids") List<Integer> uids);

    /**
     * 根据用户id差列表查询用户角色关联关系
     *
     * @param uids 用户id烈烈表
     * @return key为uid value为这个用户的角色列表
     */
    @GetMapping(value = "/user/role/map")
    Result<Map<Integer, List<UserRoleRel>>> selectUserRoleListByUids(@RequestParam("uids") List<Integer> uids);
}
