package com.springcloud.role.client;


import com.springcloud.role.entity.Role;
import com.springcloud.role.service.IRoleService;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：
 */
@RestController
public class RoleClient {
    private static final Logger logger = LoggerFactory.getLogger(RoleClient.class);

    @Resource
    private IRoleService iRoleService;

    /**
     * 新增一个角色
     *
     * @param role 角色实体类
     * @return Result<Role>
     */
    @PutMapping(value = "/role")
    public Result<Role> addRole(@RequestBody Role role) {
        iRoleService.save(role);
        return new Result<>(role);
    }
}
