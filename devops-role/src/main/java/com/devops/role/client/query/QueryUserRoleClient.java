package com.devops.role.client.query;

import com.devops.base.common.Result;
import com.devops.base.utils.JacksonUtil;
import com.devops.role.entity.UserRoleRel;
import com.devops.role.service.IUserRoleRelService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: liushuai
 * @date: 2020/10/3
 * @description：
 */
@RestController
@Api(value = "API - QueryUserClient")
public class QueryUserRoleClient {
    private static final Logger logger = LoggerFactory.getLogger(QueryUserRoleClient.class);
    @Resource
    private IUserRoleRelService iUserRoleRelService;


    /**
     * 根据用户id差列表查询用户角色关联关系
     *
     * @param uids 用户id烈烈表
     * @return key为uid value为这个用户的角色id列表
     */
    @GetMapping(value = "/user/role/id/map")
    public Result<Map<Integer, List<Integer>>> selectUserRoleIdsByUids(@RequestParam("uids") List<Integer> uids) {
        List<UserRoleRel> userRoleRels = iUserRoleRelService.selectUserRoleListByUids(uids);
        Map<Integer, List<UserRoleRel>> uidMap = userRoleRels.stream().collect(Collectors.groupingBy(UserRoleRel::getUid));
        if (CollectionUtils.isEmpty(uidMap)) {
            logger.info("查询用户关联关系为空，uids={}", JacksonUtil.toJSon(uids));
            return new Result<>(Collections.emptyMap());
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        uidMap.forEach((k, v) -> {
            if (CollectionUtils.isEmpty(v)) {
                map.put(k, Collections.emptyList());
            } else {
                map.put(k, v.stream().map(UserRoleRel::getRoleId).collect(Collectors.toList()));
            }
        });
        return new Result<>(map);
    }

    /**
     * 根据用户id差列表查询用户角色关联关系
     *
     * @param uids 用户id烈烈表
     * @return key为uid value为这个用户的角色列表
     */
    @GetMapping(value = "/user/role/map")
    public Result<Map<Integer, List<UserRoleRel>>> selectUserRoleListByUids(@RequestParam("uids") List<Integer> uids) {
        List<UserRoleRel> userRoleRels = iUserRoleRelService.selectUserRoleListByUids(uids);
        Map<Integer, List<UserRoleRel>> uidMap = userRoleRels.stream().collect(Collectors.groupingBy(UserRoleRel::getId));
        return new Result<>(uidMap);
    }

}
