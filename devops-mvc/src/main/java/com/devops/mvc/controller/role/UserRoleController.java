package com.devops.mvc.controller.role;

import com.devops.base.common.Result;
import com.devops.mvc.dto.AddUserRoleRelDto;
import com.role.api.RoleFeignApi;
import com.role.api.UserRoleFeignApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class UserRoleController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Resource
    private UserRoleFeignApi userRoleFeignApi;
    @Resource
    private RoleFeignApi roleFeignApi;

    /**
     * 为用户添加角色
     *
     * @param addUserRoleRelDto 关联关系对象
     * @return Result<Void>
     */
    @PutMapping("/mvc/user/role")
    public Result<Void> addUserRole(@RequestBody AddUserRoleRelDto addUserRoleRelDto) {
        return userRoleFeignApi.addUserRole(addUserRoleRelDto.getRoleId(), addUserRoleRelDto.getUid());
    }

    /**
     * 删除用户一个角色
     *
     * @param roleId 角色id
     * @return Result<Void>
     */
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping(value = "/mvc/user/role")
    Result<Void> deleteUserRole(@RequestParam(value = "roleId", required = false) Integer roleId,
                                @RequestParam(value = "uid", required = false) Integer uid) {
        //删除用户角色关联关系
        return userRoleFeignApi.deleteUserRole(roleId, uid);
    }
}
