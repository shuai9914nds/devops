package com.springcloud.mvc.controller.role;

import com.role.api.RoleFeignApi;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleFeignApi roleFeignApi;

    /**
     * 删除一个角色
     *
     * @param roleId 角色id
     * @return Result<Void>
     */
    @DeleteMapping(value = "/mvc/role")
    Result<Void> deleteRole(@RequestParam(value = "roleId") Integer roleId) {
        return roleFeignApi.deleteRole(roleId);
    }
}
