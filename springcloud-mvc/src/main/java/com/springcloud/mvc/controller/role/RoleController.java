package com.springcloud.mvc.controller.role;

import com.role.api.RoleFeignApi;
import com.role.api.UserRoleFeignApi;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private UserRoleFeignApi userRoleFeignApi;

    /**
     * 删除一个角色
     *
     * @param roleId 角色id
     * @return Result<Void>
     */
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping(value = "/mvc/role")
    Result<Void> deleteRole(@RequestParam(value = "roleId", required = false) Integer roleId,
                            @RequestParam(value = "uid", required = false) Integer uid) {
        //删除用户角色关联关系
        Result<Void> userRoleResult = userRoleFeignApi.deleteUserRole(roleId, uid);
        if (null == roleId) {
            return userRoleResult;
        }
        return roleFeignApi.deleteRole(roleId);
    }
}
