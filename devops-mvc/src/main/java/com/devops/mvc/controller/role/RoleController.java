package com.devops.mvc.controller.role;

import com.devops.base.common.Result;
import com.role.api.RoleFeignApi;
import com.role.api.UserRoleFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2021/1/3
 * @description：
 */
@Slf4j
@RestController
public class RoleController {

    @Resource
    private UserRoleFeignApi userRoleFeignApi;
    @Resource
    private RoleFeignApi roleFeignApi;

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
